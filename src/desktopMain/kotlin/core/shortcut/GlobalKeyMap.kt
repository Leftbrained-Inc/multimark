package core.shortcut

import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import core.configuration.Configuration
import core.configuration.Events
import core.configuration.events
import core.dsl.configuration.ConfigurationBuilder
import core.dsl.elements.shortcut.Shortcut
import core.extensions.KeyMap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import ui.utils.Scale

private var globalKeyMap = GlobalKeyMap()

/**
 * Глобальные горячие клавиши
 *
 * @author Панков Вася (pank-su)
 */
class GlobalKeyMap : KeyMap {

    lateinit var configuration: Configuration

    // Тестовая горячая клавиша
    var testShortcut = Shortcut({ KeyMap.metaKey(it) && it.key == Key.K }) { println("Worked") }

    // Горячая клавиша для приблежения
    var zoomShortcutAdd = Shortcut({ KeyMap.metaKey(it) && it.key == Key.Equals }) {
        Scale.scale += 0.1f
    }

    // Горячая клавиша для отдаления
    var zoomShortcutSub =
        Shortcut({ KeyMap.metaKey(it) && it.key == Key.Minus }) {
            if (Scale.scale > 0.5) {
                Scale.scale -= 0.1f
            }
        }

    var navigationBackShortcut =
        Shortcut({
            it.key == Key.Escape
        }) {
            CoroutineScope(SupervisorJob() + Dispatchers.Main).launch {
                events.send(Events.OnBackPressed)
            }
        }

    override fun getAllShorts(): List<Shortcut> {
        return listOf(testShortcut, zoomShortcutAdd, zoomShortcutSub, navigationBackShortcut)
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
