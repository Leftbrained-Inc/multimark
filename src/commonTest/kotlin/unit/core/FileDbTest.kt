package unit.core

import core.db.Db
import core.db.PinnedFile
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.AfterClass
import org.junit.Test

class FileDbTest {

    val connect = Db.connect

    companion object {
        /**
         * Удаление таблицы после тестов
         * @author Панков Вася (pank-su)
         */
        @JvmStatic
        @AfterClass
        fun clearDb() {
            transaction {
                PinnedFile.all().forEach {
                    it.delete()
                }
                SchemaUtils.drop(PinnedFile.table)
            }

        }
    }

    /**
     * Проверка работоспособности добавление
     * @author Панков Вася (pank-su)
     */
    @Test
    fun addingTest() {
        transaction {
            val before = PinnedFile.all().count()
            PinnedFile.new {
                fileName = "Adding"
                filePath = "test"
            }
            val after = PinnedFile.all().count()
            assert(after - 1 == before)
        }
    }

    /**
     * Проверка работоспособности удаления
     * @author Панков Вася (pank-su)
     */
    @Test
    fun deleteFromDbTest() {
        transaction {
            val before = PinnedFile.all().count()
            val file = PinnedFile.new {
                fileName = "Deleting"
                filePath = "test"
            }
            var after = PinnedFile.all().count()
            assert(after - 1 == before)
            file.delete()
            after = PinnedFile.all().count()
            assert(after == before)
        }
    }

    /**
     * Проверка работоспособности обновления
     * @author Панков Вася (pank-su)
     */
    @Test
    fun updateFromDbTest() {
        transaction {
            val fileBefore = PinnedFile.new {
                fileName = "Update"
                filePath = "what"
            }
            fileBefore.filePath = "test"
        }
    }
}