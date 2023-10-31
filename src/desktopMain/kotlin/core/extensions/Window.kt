package core.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.loadSvgPainter
import androidx.compose.ui.res.useResource
import androidx.compose.ui.unit.Density
import core.configuration.Configuration
import core.dsl.ConfigurationTagMaker
import core.dsl.configuration.ConfigurationBuilder

private var window = Window()

@ConfigurationTagMaker
data class Window(
    var icon: Painter = useResource("logo.svg") { loadSvgPainter(it, Density(100f)) },
    var title: String = "MultiMark"
)

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
 */
fun ConfigurationBuilder.window(init: Window.() -> Unit) {
    val window = Window()
    window.apply(init)
    this.window = window
}
