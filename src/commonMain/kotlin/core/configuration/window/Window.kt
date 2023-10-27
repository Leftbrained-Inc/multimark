package core.configuration.window

import core.dsl.ConfigurationTagMaker

/**
 * Реализация Window
 *
 * @property icon
 * @property title
 */
@ConfigurationTagMaker
class Window(var icon: Any, var title: String) {
}