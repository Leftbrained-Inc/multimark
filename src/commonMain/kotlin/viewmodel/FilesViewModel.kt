package viewmodel

import core.db.Db
import core.db.RecentFile
import kotlinx.coroutines.flow.flow
import kotlinx.io.files.Path
import org.jetbrains.exposed.sql.transactions.transaction

class FilesViewModel {

    val connect = Db.connect


    val recentFiles = flow {
        emit(transaction {
            return@transaction RecentFile.all().toList()
        })
    }

    fun addRecentFile(path: Path) {
        transaction {
            RecentFile.new {
                fileName = path.name
                filePath = path.toString()
            }
        }
    }
}