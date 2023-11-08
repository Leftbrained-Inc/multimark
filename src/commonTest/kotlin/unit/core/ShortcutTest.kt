package unit.core

import androidx.compose.ui.graphics.Color
import core.dsl.elements.shortcut.shorts
import org.junit.Test
import ui.utils.hex

/** Модульные тесты для горячих клавиш
 * @author Сергей Рейнн (bulkabuka)
 */
@Deprecated("Будет заменён на UI тест")
class ShortcutTest {
    /// Тест вызова действия для каждого сочетания
    @Test
    fun invocationTest() {
        println(Color.Blue.value.hex)
        shorts.forEach { it.action() }
    }

    /// Тест уникальности сочетания
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