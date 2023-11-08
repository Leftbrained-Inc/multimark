package core.db

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

/**
 * Класс для работы с базой данных
 * @author Сергей Рейнн (bulkabuka)
 * @author Панков Вася (pank-su)
 */
class Db {
    companion object {
        val connect by lazy {
            val db = Database.connect("jdbc:sqlite:main.db", "org.sqlite.JDBC")
            transaction {
                SchemaUtils.create(PinnedFiles)
            }
            return@lazy db
        }
    }
}

