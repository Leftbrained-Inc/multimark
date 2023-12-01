package core.configuration

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import core.dsl.ConfigurationTagMaker
import di.appModule
import org.koin.compose.KoinApplication
import ui.theme.MultimarkAppTheme
import ui.utils.Scale


/**
 * Главный элемент конфигурации, непривязанный к нативной реализации
 * @property logo иконка изображения, которая является compose элементом
 * @property theme тема приложения
 * @property scale информация о начальном масштабе интерфейсе (1f = 100 %, 1.5f = 250 %)
 * @property fontScale информация о начальном масштабе шрифта интерфейса (1f = 100 %, 1.5f = 250 %)
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

    var launchScreen: LaunchScreen = LaunchScreen()

    var name: String = "Multimark"

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

    /**
     * Функция запуска приложения с нативными и ненативными компонентами, инициализирующая конфигурацию
     * @param nativeContent нативный контент
     * @author Панков Вася (pank-su)
     */
    @Composable
    internal fun content(nativeContent: @Composable (@Composable (@Composable () -> Unit) -> Unit) -> Unit) {
        CompositionLocalProvider(LocalConfiguration provides this as ConfigurationImpl) {
            val config = LocalConfiguration.current

            KoinApplication(application = { modules(appModule()) }) {
                nativeContent { content ->
                    LaunchedEffect(config.scale) {
                        Scale.scale = config.scale
                        Scale.fontScale = config.fontScale
                    }
                    AnimatedContent(Scale.scale) {
                        config.theme {
                            content()
                        }
                    }
                }
            }

        }
    }
}
