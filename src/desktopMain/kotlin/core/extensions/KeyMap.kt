package core.extensions

import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.isAltPressed
import androidx.compose.ui.input.key.key
import core.configuration.Configuration
import core.dsl.configuration.ConfigurationBuilder
import core.dsl.elements.shortcut.Shortcut
import core.extensions.KeyMap.Companion.metaKey
import ui.utils.Scale


private var globalKeyMap = GlobalKeyMap()

/**
 * Интерфейс для KeyMap
 *
 * @property metaKey мета-клавиша, которая часта используется для shortcuts
 * @property shorts горячие клавиш
 *
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
     *
     * @author Панков Вася (pank-su)
     */
    fun getAllShorts(): List<Shortcut>
}

/**
 * Глобальные горячие клавиши
 *
 * @author Панков Вася (pank-su)
 */
class GlobalKeyMap : KeyMap {

    // Тестовая горячая клавиша
    var testShortcut = Shortcut({ metaKey(it) && it.key == Key.K }) { println("Worked") }

    // Горячая клавиша для приблежения
    var zoomShortcutAdd = Shortcut({ metaKey(it) && it.key == Key.Equals }) {
        Scale.scale += 0.1f
    }

    // Горячая клавиша для отдаления
    var zoomShortcutSub =
        Shortcut({ metaKey(it) && it.key == Key.Minus }) { if (Scale.scale > 0.5) {
            Scale.scale -= 0.1f
        } }

    override fun getAllShorts(): List<Shortcut> {
        return listOf(testShortcut, zoomShortcutAdd, zoomShortcutSub)
    }
}

/**
 * Добавление keyMap в конфигурацию
 *
 * @author Панков Вася (pank-su)
 */
var Configuration.keyMap: GlobalKeyMap
    get() = globalKeyMap
    set(value) {
        globalKeyMap = value
    }

/**
 * Добавление keyMap в сборщик конифгурации
 *
 * @author Панков Вася (pank-su)
 */
fun ConfigurationBuilder.keyMap(init: GlobalKeyMap.() -> Unit) {
    val globalKeyMap = GlobalKeyMap()
    globalKeyMap.init()
    this.keyMap = globalKeyMap
}

