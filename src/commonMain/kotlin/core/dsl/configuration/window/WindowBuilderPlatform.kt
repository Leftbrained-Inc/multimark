package core.dsl.configuration.window

import core.configuration.window.Window

/**
 * Абстракция для реализации навтивных компонентов
 *
 * @author Панков Вася (pank-su)
 */
expect abstract class WindowBuilderPlatform() {
    /**
     * Функция сборки информации о окне
     *
     */
    open fun build(): Window
}