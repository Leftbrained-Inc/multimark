package unit

import core.db.Db
import core.db.PinnedFileDao
import org.junit.Test

class FileDbTest {

    /**
     * Тест проверяет работоспособность базы
     */
    @Test
    fun simpleTest() {
        val db = Db()
    }

    /**
     * Тест проверяет работоспособность добавление в БД
     */
    @Test
    fun addingTest() {
        val db = PinnedFileDao()
        val before = db.select().size
        db.insert("Test", "Test")
        val after = db.select().size
        assert(after - 1 == before)
    }
}