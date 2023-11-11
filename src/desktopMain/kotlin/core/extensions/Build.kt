package core.extensions

import core.configuration.ConfigurationImpl
import core.dsl.configuration.ConfigurationBuilder
import core.shortcut.keyMap

/**
 * Добавление нативных дополнений при сборке
 * @author Панков Вася (pank-su)
 */
fun ConfigurationBuilder.build(): ConfigurationImpl {
    val configuration = this.build()
    configuration.keyMap = this.keyMap
    configuration.keyMap.configuration = configuration
    configuration.window = this.window
    return configuration
}