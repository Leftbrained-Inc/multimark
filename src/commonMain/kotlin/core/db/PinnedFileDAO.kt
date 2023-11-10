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
 * @author Панков Вася (pank-su)
 */
object PinnedFiles : IntIdTable() {
    val file = reference("file", RecentFiles)
    val dateAdding = datetime("date_add").default(Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()))
}

/**
 * Entity для работы с закреплёнными файлами в БД
 * @author Панков Вася (pank-su)
 */
class PinnedFile(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<PinnedFile>(PinnedFiles)

    var file by RecentFile referencedOn PinnedFiles.file
    var dateAdding by PinnedFiles.dateAdding
}