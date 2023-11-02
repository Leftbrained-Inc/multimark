package core.configuration

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import core.dsl.ConfigurationTagMaker
import ui.theme.MultimarkAppTheme


/**
 * Главный элемент конфигурации, непривязанный к нативной реализации
 *
 *
 * @property logo иконка изображения, которая является compose элементом
 * @property theme тема приложения
 * @property scale информация о начальном масштабе интерфейсе (1f = 100 %, 1.5f = 250 %)
 * @property fontScale информация о начальном масштабе шрифта интерфейса (1f = 100 %, 1.5f = 250 %)
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

    private var _scale: Float = 1.0f
    private var _fontScale: Float = 1.0f

    var scale
        set(value) {

            if (value < 0.5f) {
                _scale = 0.5f
                return
            }
            _scale = value
        }
        get() = _scale

    var fontScale
        set(value) {

            if (value < 0.5f) {
                _fontScale = 0.5f
                return
            }
            _fontScale = value
        }
        get() = _fontScale
}
