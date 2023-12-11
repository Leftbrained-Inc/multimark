package core.extensions

import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.loadSvgPainter
import androidx.compose.ui.res.useResource
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowState
import core.configuration.Configuration
import core.dsl.ConfigurationTagMaker
import core.dsl.configuration.ConfigurationBuilder

private var window = Window()

/**
 * Настройки окна
 * @property icon иконка
 * @property title названия окна
 * @author Василий Панков (pank-su)
 */
@ConfigurationTagMaker
data class Window(
    var icon: Painter = useResource("logo.svg") { loadSvgPainter(it, Density(100f)) },
    var title: String = "Multimark",
    val state: WindowState = WindowState(size = DpSize(800.dp, 658.dp))
)

/**
 * Добавление переменной для конфигурации
 * @author Василий Панков (pank-su)
 */
var Configuration.window: Window
    get() = core.extensions.window
    set(value) {
        core.extensions.window = value
    }

/**
 * Установка настроек окна (иконки и названия)
 *
 * ```kotlin
 * val painter = painterResource("Good Tick.svg")
 * config {
 *      window {
 *          icon = painter
 *          title = "Simple title"
 *      }
 * }
 * ```
 * @author Василий Панков (pank-su)
 */
fun ConfigurationBuilder.window(init: Window.() -> Unit) {
    val window = Window()
    window.apply(init)
    this.window = window
}