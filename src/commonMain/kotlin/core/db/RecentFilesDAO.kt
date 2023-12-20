package core.db

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

/**
 * Таблица закреплённых файлов
 * @author Сергей Рейнн (bulkabuka)
 * @author Василий Панков (pank-su)
 */
object RecentFiles : IntIdTable() {
    val filePath = text("file_path").uniqueIndex()
    val fileName = text("file_name")
    val dateOfApplication =
        datetime("date_of_application").default(Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()))
}

class RecentFile(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<RecentFile>(RecentFiles)

    var filePath by RecentFiles.filePath
    var fileName by RecentFiles.fileName
    var dateOfApplication by RecentFiles.dateOfApplication

}