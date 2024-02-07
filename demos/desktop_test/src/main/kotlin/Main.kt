import androidx.compose.foundation.Image
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.isAltPressed
import androidx.compose.ui.input.key.isCtrlPressed
import androidx.compose.ui.input.key.key
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import core.configuration.FilesMode
import core.dsl.configuration.config
import core.dsl.elements.shortcut.Shortcut
import core.extensions.KeyMap
import core.shortcut.keyMap

fun main() {
    config {
        logo {
            Image(
                painterResource("Good Tick.svg"), null, modifier = it,
                contentScale = ContentScale.FillHeight
            )
        }

        scale = 1f
        fontScale = 1f

        keyMap {
            KeyMap.metaKey = { it.isCtrlPressed }
            testShortcut = Shortcut({ keyEvent -> keyEvent.isAltPressed && keyEvent.key == Key.K }) {
                println("Test")
            }
        }

        launchScreen {
            filesMode = FilesMode.Both
            recentFilter {
                it.fileName.contains("md")
            }
        }
    }
}
