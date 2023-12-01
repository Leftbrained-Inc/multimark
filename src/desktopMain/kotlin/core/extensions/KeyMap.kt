package core.extensions

import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.isAltPressed
import core.dsl.elements.shortcut.Shortcut
import core.extensions.KeyMap.Companion.metaKey


/**
 * Интерфейс для KeyMap
 * @property metaKey мета-клавиша, которая часта используется для shortcuts
 * @property shorts горячие клавиш
 * @author Панков Вася (pank-su)
 */
interface KeyMap {
    companion object {
        var metaKey: (KeyEvent) -> Boolean = {
            it.isAltPressed
        }
    }

    val shorts
        get(): List<Shortcut> = getAllShorts()

    /**
     * Получение всех горячих клавиш
     * @author Панков Вася (pank-su)
     */
    fun getAllShorts(): List<Shortcut>
}