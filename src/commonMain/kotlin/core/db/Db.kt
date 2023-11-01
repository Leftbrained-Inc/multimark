package core.db

import org.jetbrains.exposed.sql.*

/**
 * Класс для работы с базой данных
 * @author Сергей Рейнн (bulkabuka)
 * @author Панков Вася (pank-su)
 */
class Db {
    companion object {
        val connect by lazy { Database.connect("jdbc:sqlite:main.db", "org.sqlite.JDBC") }
    }
}

