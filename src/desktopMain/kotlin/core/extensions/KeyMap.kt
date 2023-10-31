package core.extensions

import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.isAltPressed
import androidx.compose.ui.input.key.key
import core.configuration.Configuration
import core.configuration.ConfigurationImpl
import core.dsl.configuration.ConfigurationBuilder
import core.dsl.elements.shortcut.Shortcut


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

    var testShortcut = Shortcut({ KeyMap.metaKey(it) && it.key == Key.K }) { println("Worked") }
    override fun getAllShorts(): List<Shortcut> {
        return listOf(testShortcut)
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

