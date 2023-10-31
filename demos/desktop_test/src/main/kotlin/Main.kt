import androidx.compose.foundation.Image
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.isAltPressed
import androidx.compose.ui.input.key.key
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.application
import core.dsl.configuration.config
import core.dsl.elements.shortcut.Shortcut
import core.extensions.KeyMap
import core.extensions.keyMap

fun main() {
    application {
        val painter = painterResource("Good Tick.svg")
        config {
            logo {
                Image(painterResource("Good Tick.svg"), null, modifier = it)
            }

            scale = 1f

            window {
                icon = painter
                title = "Test"
            }
            keyMap {
                KeyMap.metaKey = { it.key == Key.Minus }
                testShortcut = Shortcut({ keyEvent -> keyEvent.isAltPressed }) {
                    println("Test")
                }
            }
        }.render(::exitApplication)
    }
}
