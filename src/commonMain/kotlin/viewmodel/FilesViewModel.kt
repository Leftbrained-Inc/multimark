package viewmodel

import core.db.Db
import core.db.RecentFile
import core.db.RecentFiles
import kotlinx.coroutines.flow.flow
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.io.files.Path
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

/**
 * ViewModel для работы с недавними файлами
 * @author Панков Вася (pank-su)
 */
class FilesViewModel {
    val connect = Db.connect
    val recentFiles = flow {
        emit(transaction {
            return@transaction RecentFile.all().toList()
        })
    }

    fun addRecentFile(path: Path) {
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
    }
}