package core.dsl.elements.configuration

import androidx.compose.runtime.Composable

/**
 * Реализация нативных компонентов
 *
 * [render(onCloseRequest)] - отображение с учитыванием закрытия окна
 * @author Панков Вася (pank-su)
 */
expect abstract class ConfigurationPlatform() : Configuration {

    @Composable
    open fun render(onCloseRequest: () -> Unit)
}