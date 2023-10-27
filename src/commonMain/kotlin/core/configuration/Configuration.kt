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
 * @property icon иконка изображения, которая является compose элементом
 * @property theme тема приложения
 *
 * @author Панков Вася (pank-su)
 */
@ConfigurationTagMaker
abstract class Configuration {
    var icon: @Composable (modifier: Modifier) -> Unit =
        { Image(painterResource("logo.svg"), null, modifier = it) }

    var theme: @Composable (content: @Composable () -> Unit) -> Unit = {
        MultimarkAppTheme {
            it()
        }
    }
}