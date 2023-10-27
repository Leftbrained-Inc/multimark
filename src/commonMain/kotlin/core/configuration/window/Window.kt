package core.configuration.window

import core.dsl.ConfigurationTagMaker

/**
 * Реализация Window
 *
 * На разных платформах будут разные значения
 *
 * @property icon иконка
 * @property title название
 *
 * @author Панков Вася (pank-su)
 */
@ConfigurationTagMaker
data class Window(var icon: Any, var title: String)