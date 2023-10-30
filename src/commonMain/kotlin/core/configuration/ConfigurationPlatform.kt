package core.configuration

import androidx.compose.runtime.Composable

/**
 * Абстракция нативных компонентов
 * @author Панков Вася (pank-su)
 */
expect abstract class ConfigurationPlatform() : Configuration {


    /**
     * Абстракция для desktop
     * @param onCloseRequest передаётся request на закрытие приложения
     * @author Панков Вася (pank-su)
     */
    @Composable
    open fun render(onCloseRequest: () -> Unit)
}