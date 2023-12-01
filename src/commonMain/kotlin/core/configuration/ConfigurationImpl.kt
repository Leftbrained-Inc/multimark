package core.configuration

import androidx.compose.runtime.compositionLocalOf

/**
 * Конфигурация со всеми функциями (нативными и нет)
 * @author Панков Вася (pank-su)
 */
class ConfigurationImpl : ConfigurationPlatform()

/**
 * Локальная конфигурация в compose
 */
val LocalConfiguration = compositionLocalOf {
    ConfigurationImpl()
}

