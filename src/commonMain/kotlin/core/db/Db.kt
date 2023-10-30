package core.db

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

/**
 * Класс для работы с базой данных приложения
 * @author Сергей Рейнн (bulkabuka)
 */
class Db {

    companion object {
        val connect by lazy { Database.connect("jdbc:sqlite:main.db", "org.sqlite.JDBC") }
    }


    init {

    }

    /**
     * Класс для работы с закрепленными файлами в базе данных
     * @author Сергей Рейнн (bulkabuka)
     */

}

