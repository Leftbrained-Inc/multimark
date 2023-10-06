package core.dsl

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import core.dsl.elements.template.Element

actual abstract class ConfigurationPlatform actual constructor() : core.dsl.Configuration() {


    @Composable
    actual open fun render(onCloseRequest: () -> Unit) =
        Window(onCloseRequest) {
            super.render()

        }


}