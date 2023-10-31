package core.dsl.configuration

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import core.configuration.Configuration
import core.configuration.ConfigurationImpl
import core.dsl.configuration.window.WindowBuilder

/**
 * Builder конфигурации
 *
 * Необходим для того чтобы функции и переменные не перемещивались в конфигурации у разработчика
 *
 * @author Панков Вася (pank-su)
 */
class ConfigurationBuilder : Configuration() {

    /**
     * Установка иконки
     *
     * ```kotlin
     * icon {
     *   Image(painterResource("Good Tick.svg"), null, modifier = it)
     * }
     * ```
     */
    fun icon(init: @Composable (modifier: Modifier) -> Unit) {
        this.icon = init
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
    fun window(init: WindowBuilder.() -> Unit){
        val builder = WindowBuilder()
        builder.init()
        this.window = builder.build()
    }

    /**
     * Создание [ConfigurationImpl] на основе полученных данных
     *
     * @return ConfigurationImpl - мультплатформенная реализация конфигурации
     */
    internal fun build(): ConfigurationImpl {
        val configImpl = ConfigurationImpl()
        configImpl.icon = icon
        configImpl.window = window
        configImpl.scale = scale
        return configImpl
    }
}