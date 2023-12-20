package core.extensions

import core.configuration.ConfigurationImpl
import core.dsl.configuration.ConfigurationBuilder
import core.extensions.window.window
import core.shortcut.keyMap

/**
 * Добавление нативных дополнений при сборке
 * @author Василий Панков (pank-su)
 */
fun ConfigurationBuilder.build(): ConfigurationImpl {
    val configuration = this.build()
    configuration.keyMap = this.keyMap
    configuration.keyMap.configuration = configuration
    configuration.window = this.window
    return configuration
}