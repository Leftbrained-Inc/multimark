package core.dsl.elements.configuration

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.input.key.key
import androidx.compose.ui.window.Window

actual abstract class ConfigurationPlatform actual constructor() : Configuration() {

    @Composable
    actual open fun render(onCloseRequest: () -> Unit) =
        Window(onCloseRequest, onKeyEvent = { keyEvent ->
            shorts.forEach {
                if (it.condition(keyEvent)) {
                    it.action()
                    return@Window true
                }
            }
            false
        }) {
            super.render()
        }
}