package core.dsl.configuration.window

import core.configuration.window.Window

expect abstract class WindowBuilderPlatform() {
    open fun build(): Window
}