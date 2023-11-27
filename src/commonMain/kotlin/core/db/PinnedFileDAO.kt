package core.db

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

/**
 * Таблица закреплённых файлов
 * @author Сергей Рейнн (bulkabuka)
 * @author Панков Вася (pank-su)
 */
object PinnedFiles : IntIdTable() {
    val filePath = text("file_path")
    val fileName = text("file_name")
}

class PinnedFile(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<PinnedFile>(PinnedFiles)

    var filePath by PinnedFiles.filePath
    var fileName by PinnedFiles.fileName
}