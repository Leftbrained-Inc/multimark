package core.configuration

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import core.configuration.window.Window
import core.dsl.ConfigurationTagMaker
import core.dsl.configuration.window.WindowBuilder
import ui.theme.MultimarkAppTheme


/**
 * Главный элемент конфигурации, непривязанный к нативной реализации
 *
 *
 * @property logo иконка изображения, которая является compose элементом
 * @property theme тема приложения
 * @property window информация об окне
 * @property scale информация о scale gui
 *
 * @author Панков Вася (pank-su)
 */
@ConfigurationTagMaker
abstract class Configuration {
    var logo: @Composable (modifier: Modifier) -> Unit =
        { Image(painterResource("logo.svg"), null, modifier = it) }

    var theme: @Composable (content: @Composable () -> Unit) -> Unit = {
        MultimarkAppTheme {
            it()
        }
    }

    var window: Window = WindowBuilder().build()

    private var _scale: Float = 1.0f

    var scale
        set(value) {

            if (value < 0.5f) {
                _scale = 0.5f
                return
            }
            _scale = value
        }
        get() = _scale
}
