package core.configuration

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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


/**
 * Начальный экран
 *
 * @property mode режим начального экрана
 * - [LaunchScreenMode.Default] - обычный стартовый экран с возможностью открыть файл и посмотреть последние открытые
 * - TODO [LaunchScreenMode.Scratch] - режим заметок, открывается пустое поле для редактирования, которое можно редактрировать
 *
 * @property filesMode режим просмотра файлов в обычном режиме
 * - [FilesMode.Both] - закреплённые и недавние
 * - [FilesMode.Pinned] - Только закреплённые
 * - [FilesMode.Recent] - Только последние файла
 *
 *
 * @author pank-su (Василий Панков)
 */
class LaunchScreen {
    var filesMode = FilesMode.Both

    var mode: LaunchScreenMode = LaunchScreenMode.Default

    private var _recentFilter: (RecentFile) -> Boolean = { true }

    /**
     * Фильтрация недавних файлов
     */
    fun recentFilter(filter: (RecentFile) -> Boolean) {
        this._recentFilter = filter
    }

    private val _recentFiles = MutableStateFlow<List<RecentFile>>(listOf())


    val recentFiles = _recentFiles.asStateFlow()

    var isFirstLoad by mutableStateOf(true)

    init {
        Db
        CoroutineScope(Dispatchers.IO).launch {
            reloadFiles()
        }
    }

    /**
     * Обновление списка файлов
     */
    private suspend fun reloadFiles() {
        _recentFiles.emit(transaction {
            return@transaction RecentFile.all().filter { _recentFilter(it) }.toList()
        })
    }

    /**
     * Добавление недавнего файла
     *
     * @param path путь к файлу
     */
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