package core.configuration

import androidx.compose.runtime.compositionLocalOf

/**
 * Конфигурация со всеми функциями (нативными и нет)
 * @author Василий Панков (pank-su)
 */
class ConfigurationImpl : ConfigurationPlatform()

/**
 * Локальная конфигурация в compose
 */
val LocalConfiguration = compositionLocalOf {
    ConfigurationImpl()
}

