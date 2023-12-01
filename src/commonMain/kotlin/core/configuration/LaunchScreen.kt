package core.configuration

import core.db.Db
import core.db.RecentFile
import core.db.RecentFiles
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.io.files.Path
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

sealed class LaunchScreenMode {
    data object Default : LaunchScreenMode()
    class Scratch : LaunchScreenMode()
}

class LaunchScreen {
    var filesMode = FilesMode.Both

    var mode: LaunchScreenMode = LaunchScreenMode.Default

    private var _recentFilter: (RecentFile) -> Boolean = { true }

    fun recentFilter(filter: (RecentFile) -> Boolean) {
        this._recentFilter = filter
    }

    private val _recentFiles = MutableStateFlow<List<RecentFile>>(listOf())

    val recentFiles = _recentFiles.asStateFlow()

    init {
        Db
        CoroutineScope(Dispatchers.IO).launch {
            reloadFiles()
        }
    }

    private suspend fun reloadFiles() {
        _recentFiles.emit(transaction {
            return@transaction RecentFile.all().filter { _recentFilter(it) }.toList()
        })
    }

    suspend fun addRecentFile(path: Path) {
        transaction {
            val file = RecentFile.find(RecentFiles.filePath eq path.toString())

            if (file.empty())
                RecentFile.new {
                    fileName = path.name
                    filePath = path.toString()
                }
            else
                file.first().dateOfApplication = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        }
        reloadFiles()
    }

}