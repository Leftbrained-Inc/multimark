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
    data object Scratch : LaunchScreenMode()
}


/**
 * Начальный экран
 * @property mode Режим начального экрана
 * - [LaunchScreenMode.Default] - Обычный стартовый экран с возможностью открыть файл и посмотреть последние открытые
 * - TODO [LaunchScreenMode.Scratch] - Режим заметок, открывается пустое поле для редактирования, которое можно редактрировать
 *
 * @property filesMode Режим просмотра файлов в обычном режиме
 * - [FilesMode.Both] - Закреплённые и недавние
 * - [FilesMode.Pinned] - Только закреплённые
 * - [FilesMode.Recent] - Только последние файла
 * @author Василий Панков (pank-su)
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


    internal val recentFiles = _recentFiles.asStateFlow()

    internal var isFirstLoad by mutableStateOf(true)

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