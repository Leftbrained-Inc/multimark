package unit.core

import core.db.PinnedFile
import core.db.RecentFile
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.AfterClass
import org.junit.Test

class FileDbTest {


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
                SchemaUtils.drop(RecentFile.table)
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
                file = RecentFile.new {
                    fileName = "Adding"
                    filePath = "test"
                }
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
                file = RecentFile.new {
                    fileName = "Deleting"
                    filePath = "test"
                }
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
            val file = PinnedFile.new {
                file = RecentFile.new {
                    fileName = "Deleting"
                    filePath = "test"
                }
                dateAdding = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
            }
            file.file.filePath = "test"
        }
    }
}