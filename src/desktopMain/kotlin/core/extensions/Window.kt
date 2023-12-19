package core.extensions

import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.loadSvgPainter
import androidx.compose.ui.res.useResource
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import core.configuration.Configuration
import core.configuration.ConfigurationImpl
import core.dsl.ConfigurationTagMaker
import core.dsl.configuration.ConfigurationBuilder
import ui.components.TabCategory

private var window = Window()

/**
 * Настройки окна
 * @property icon Иконка
 * @property title Название окна
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
 * Утилита которая проверяет, находится ли что-то за пределами окна
 * @author Василий Панков (pank-su)
 */
fun Window.isOutWindow(position: Pair<Float, Float>, density: Density): Boolean {
    val windowState = state

    val xPx = with(density) { windowState.position.x.toPx() }
    val yPx = with(density) { windowState.position.y.toPx() }
    val widthPx = with(density) { windowState.size.width.toPx() + xPx }
    val heightPx = with(density) { windowState.size.height.toPx() + yPx }

    println("$position $xPx $yPx $widthPx $heightPx ")

    return position.first + xPx < xPx || position.first + xPx > widthPx || position.second + yPx > heightPx || position.second + yPx < yPx
}

/**
 * Действие если таб вне экрана
 * @author Василий Панков (pank-su)
 */
fun TabCategory.onOutWindow(configuration: ConfigurationImpl, density: Density) {
    val position = WindowPosition(with(density) { (dragTabState.position.first + dragTabState.offset.x).toDp() },
        with(density) { (dragTabState.position.second + dragTabState.offset.y).toDp() })
    val windowState = WindowState(window.state.placement, false, position, window.state.size)
    configuration.windows.add(
        windowState
    )

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