import androidx.compose.ui.input.key.isAltPressed
import androidx.compose.ui.input.key.isCtrlPressed
import androidx.compose.ui.res.loadSvgPainter
import androidx.compose.ui.res.useResource
import androidx.compose.ui.unit.Density
import core.configuration.FilesMode
import core.dsl.configuration.config
import core.dsl.elements.shortcut.Shortcut
import core.extensions.KeyMap
import core.extensions.window
import core.shortcut.keyMap

fun main() {
    config {
//        logo {
//            Image(painterResource("Good Tick.svg"), null, modifier = it, contentScale = ContentScale.FillHeight)
//        }

        scale = 1f
        fontScale = 1f

        window {
            icon = useResource("Good Tick.svg") { loadSvgPainter(it, Density(100f)) }
            title = "Test"
        }

        keyMap {
            KeyMap.metaKey = { it.isCtrlPressed }
            testShortcut = Shortcut({ keyEvent -> keyEvent.isAltPressed }) {
                println("Test")
            }
        }

        launchScreen {
            filesMode = FilesMode.Both

            recentFilter {
                true
            }
        }
    }
}
