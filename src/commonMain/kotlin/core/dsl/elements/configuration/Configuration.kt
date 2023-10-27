package core.dsl.elements.configuration

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import core.dsl.ConfigurationTagMaker
import core.dsl.elements.template.Element
import ui.theme.MultimarkAppTheme


/**
 * Главный элемент конфигурации, непривязанный к нативной реализации
 * @property icon Изображение, которое является Compose-элементом
 * @property theme Тема приложения
 * @author Панков Вася (pank-su)
 */
@ConfigurationTagMaker
abstract class Configuration : Element {
    var icon: @Composable (modifier: Modifier) -> Unit =
        { Image(painterResource("logo.svg"), null, modifier = it) }

    var theme: @Composable (content: @Composable () -> Unit) -> Unit = {
        MultimarkAppTheme {
            it()
        }
    }
}
