package core.configuration

import core.db.Db
import core.db.RecentFile
import core.db.RecentFiles
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.io.files.Path
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction


class LaunchScreen {

    init {
        Db
    }

    var filesMode = FilesMode.Both

    private var filter: (RecentFile) -> Boolean = { true }

    fun filter(filter: (RecentFile) -> Boolean) {
        this.filter = filter
    }

    private val _recentFiles = MutableStateFlow<List<RecentFile>>(listOf())

    val recentFiles = _recentFiles.asStateFlow()

    private suspend fun reloadFiles() {
        _recentFiles.emit(transaction {
            return@transaction RecentFile.all().toList()
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