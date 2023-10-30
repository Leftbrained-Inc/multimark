package unit.core

import core.db.PinnedFileDAO
import org.junit.After
import org.junit.Test

class FileDbTest {

    /**
     * Удаление таблицы после тестов
     * @author Панков Вася (pank-su)
     */
    @After
    fun clearDb() {
        val db = PinnedFileDAO()
        db.remove()
    }

    /**
     * Проверка работоспособности добавление
     * @author Панков Вася (pank-su)
     */
    @Test
    fun addingTest() {
        val db = PinnedFileDAO()
        val before = db.select().size
        db.insert("Test", "Test")
        val after = db.select().size
        assert(after - 1 == before)
    }

    /**
     * Проверка работоспособности удаления
     * @author Панков Вася (pank-su)
     */
    @Test
    fun deleteFromDbTest() {
        val db = PinnedFileDAO()
        val before = db.select().size
        val id = db.insert("Deleted", "pinned")
        var after = db.select().size
        assert(after - 1 == before)
        db.delete(id)
        after = db.select().size
        assert(after == before)
    }

    /**
     * Проверка работоспособности обновления
     * @author Панков Вася (pank-su)
     */
    @Test
    fun updateFromDbTest() {
        val db = PinnedFileDAO()
        val id = db.insert("Updated", "pinned")
        val before = db[id]
        db.update(id, "new", "new")
        val after = db[id]
        assert(after[PinnedFileDAO.Pinned.id].value == before[PinnedFileDAO.Pinned.id].value)
        assert(after[PinnedFileDAO.Pinned.fileName] != before[PinnedFileDAO.Pinned.fileName])
        assert(after[PinnedFileDAO.Pinned.filePath] != before[PinnedFileDAO.Pinned.filePath])
        assert(after[PinnedFileDAO.Pinned.fileName] == "new")
        assert(after[PinnedFileDAO.Pinned.filePath] == "new")
    }
}