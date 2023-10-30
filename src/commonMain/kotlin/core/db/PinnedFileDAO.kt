package core.db

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

class PinnedFileDAO {

    /**
     * Таблица закреплённых файлов
     * @author Сергей Рейнн (bulkabuka)
     * @author Панков Вася (pank-su)
     */
    object Pinned : IntIdTable() {
        val filePath = text("file_path")
        val fileName = text("file_name")
    }

    /**
     * Создание таблицы, если она не была создана
     * @author Панков Вася (pank-su)
     */
    init {
        Db.connect
        transaction {
            SchemaUtils.create(Pinned)
        }
    }

    /**
     * Получение всех строк таблицы
     * @return все строки таблицы
     * @author Панков Вася (pank-su)
     */
    fun select(): List<ResultRow> {
        return transaction {
            Pinned.selectAll().toList()
        }
    }

    /**
     * Добавление нового закрепленного файла
     * @param fileName Имя файла
     * @param filePath Путь к файлу
     * @return ID созданной строки
     * @author Сергей Рейнн (bulkabuka)
     * @author Панков Вася (pank-su)
     */
    fun insert(fileName: String, filePath: String): Int {
        return transaction {
            Pinned.insertAndGetId {
                it[Pinned.fileName] = fileName
                it[Pinned.filePath] = filePath
            }.value
        }
    }

    /** Обновление закрепленного файла по его id
     * @param id Идентификатор закрепленного файла
     * @param fileName Новое имя файла
     * @param filePath Новый путь к файлу
     * @author Сергей Рейнн (bulkabuka)
     */
    fun update(id: Int, fileName: String, filePath: String) {
        transaction {
            Pinned.update({ Pinned.id eq id }) {
                it[Pinned.fileName] = fileName
                it[Pinned.filePath] = filePath
            }
        }
    }

    /** Удаление информации о закрепленном файле по id
     * @param id Идентификатор закрепленного файла, который нужно удалить из базы данных
     * @author Сергей Рейнн (bulkabuka)
     */
    fun delete(id: Int) {
        transaction {
            Pinned.deleteWhere { Pinned.id eq id }
        }
    }

    /**
     * Полное удаление таблицы
     *
     * @author Панков Вася (pank-su)
     */
    fun remove() {
        transaction {
            Pinned.deleteAll()
            SchemaUtils.drop(Pinned)
        }
    }

    /**
     * Получение строки по id
     * ```kotlin
     * val before = db[id]
     * ```
     * @param id идентификатор закрплённого файла
     * @return [ResultRow] - Строка из таблицы
     * @author Панков Вася (pank-su)
     */
    operator fun get(id: Int): ResultRow {
        return transaction {
            Pinned.select { Pinned.id eq id }.toList().first()
        }
    }
}