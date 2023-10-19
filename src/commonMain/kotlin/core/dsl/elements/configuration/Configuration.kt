package core.dsl.elements.configuration

import App
import androidx.compose.foundation.Image
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import core.dsl.ConfigurationTagMaker
import core.dsl.elements.template.Element


val LocalConfiguration = compositionLocalOf {
    config {
    }
}

/**
 * Главный элемент конфигурации, непривязанный к нативной реализации
 *
 * [icon] - иконка изображения, которая является compose элементом
 * [render] - отображение элемента, показывается стартовый экран
 * @author Панков Вася (pank-su)
 */
@ConfigurationTagMaker
abstract class Configuration : Element {
    var icon: @Composable (modifier: Modifier) -> Unit =
        { Image(painterResource("logo.svg"), null, modifier = it) }

    @Composable
    override fun render() {
        CompositionLocalProvider(LocalConfiguration provides this as ConfigurationImpl) {
            MaterialTheme {
                App()
            }
        }
    }
}
