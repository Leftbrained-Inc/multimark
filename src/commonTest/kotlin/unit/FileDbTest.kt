package unit

import core.db.Db
import core.db.PinnedFileDao
import org.junit.Test

class FileDbTest {

    /**
     * Проверка работоспособности
     */
    @Test
    fun simpleTest() {
        val db = Db()
    }

    /**
     * Проверка работоспособности добавление
     */
    @Test
    fun addingTest() {
        val db = PinnedFileDao()
        val before = db.select().size
        db.insert("Test", "Test")
        val after = db.select().size
        assert(after - 1 == before)
    }

    /**
     * Проверка работоспособности удаления
     */
    @Test
    fun deleteFromDbTest(){
        val db = PinnedFileDao()
        val before = db.select().size
        val id = db.insert("Deleted", "pinned")
        var after = db.select().size
        assert(after - 1 == before)
        db.delete(id)
        after = db.select().size
        assert(after == before)
    }
}