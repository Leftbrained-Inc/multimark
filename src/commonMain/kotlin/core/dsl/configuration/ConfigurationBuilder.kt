package core.dsl.configuration

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import core.configuration.Configuration
import core.configuration.ConfigurationImpl

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
    fun logo(init: @Composable (modifier: Modifier) -> Unit) {
        this.logo = init
    }


    /**
     * Создание [ConfigurationImpl] на основе полученных данных
     *
     * @return ConfigurationImpl - мультплатформенная реализация конфигурации
     */
    internal fun build(): ConfigurationImpl {
        val configImpl = ConfigurationImpl()
        configImpl.logo = logo
        configImpl.scale = scale
        configImpl.fontScale = fontScale
        return configImpl
    }
}