package core.dsl.elements.configuration

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


/**
 * Конфигурация со всеми функциями (нативными и нет)
 *
 * [icon] - функция для назначения иконки
 *
 * @author Панков Вася (pank-su)
 */
class ConfigurationImpl : ConfigurationPlatform() {

    fun icon(init: @Composable (modifier: Modifier) -> Unit) {
        this.icon = init
    }

}

/**
 * Реализует настройку [ConfigurationImpl] с помощью DSL
 */
fun config(init: ConfigurationImpl.() -> Unit): ConfigurationImpl {
    val config = ConfigurationImpl()
    config.init()
    return config
}