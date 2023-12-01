package core.db

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

/**
 * Класс для работы с базой данных
 * @author Сергей Рейнн (bulkabuka)
 * @author Панков Вася (pank-su)
 */
object Db {
    init {
        Database.connect("jdbc:sqlite:main.db", "org.sqlite.JDBC")
        transaction {
            SchemaUtils.create(PinnedFiles)
        }
    }
}