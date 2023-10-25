import core.dsl.elements.shortcut.shorts
import org.junit.Test

// Модульные тесты для горячих клавиш
// Автор: Сергей Рейнн <bulkabuka>
class ShortcutTest {
    // Тест вызова действия для каждого сочетания
    @Test
    fun invocationTest() {
        shorts.forEach { it.action() }
    }

    // Тест уникальности сочетания
    @Test
    fun uniquenessTest() {
        shorts.forEach { shortcut ->
            shorts.forEach { otherShortcut ->
                if (shortcut != otherShortcut) {
                    assert(shortcut.condition != otherShortcut.condition)
                }
            }
        }
    }
}