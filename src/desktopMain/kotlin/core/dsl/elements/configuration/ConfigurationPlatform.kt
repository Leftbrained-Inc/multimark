package core.dsl.elements.configuration

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window

actual abstract class ConfigurationPlatform actual constructor() : Configuration() {

    @Composable
    actual open fun render(onCloseRequest: () -> Unit) =
        Window(onCloseRequest) {
            super.render()
        }
}