package core.dsl.elements.configuration

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Builder конфигурации
 *
 * Необходим для того чтобы функции и переменные не перемещивались в конфигурации у разработчика
 *
 * @author Панков Вася (pank-su)
 */
class ConfigurationBuilder : Configuration() {

    // TODO сделать переменные приватными

    /**
     * Установка иконки
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
     * Создание [ConfigurationImpl] на основе данных
     */
    internal fun build(): ConfigurationImpl {
        val configImpl = ConfigurationImpl()

        return configImpl.apply {
            this.icon = this@ConfigurationBuilder.icon
        }
    }
}