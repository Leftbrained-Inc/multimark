package core.configuration

import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.type
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.bumble.appyx.navigation.integration.DesktopNodeHost
import core.extensions.window
import core.shortcut.keyMap
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.receiveAsFlow
import navigation.RootNode


sealed class Events {
    object OnBackPressed : Events()
}

val events: Channel<Events> = Channel()


/**
 * Нативная реализация
 * @author Панков Вася (pank-su)
 */
actual abstract class ConfigurationPlatform actual constructor() : core.configuration.Configuration() {

    /**
     * Отображение экрана и навигации
     * @author Панков Вася (pank-su)
     */
    actual open fun render() {
        application {
            val windowState = rememberWindowState(size = DpSize(480.dp, 658.dp))
            content {
                val configuration = LocalConfiguration.current
                Window(this::exitApplication, onKeyEvent = { keyEvent ->
                    if (keyEvent.type == KeyEventType.KeyDown)
                        configuration.keyMap.shorts.forEach {
                            if (it.condition(keyEvent)) {
                                it.action()
                                return@Window true
                            }
                        }
                    false
                }, icon = configuration.window.icon, title = configuration.window.title) {
                    it {
                        DesktopNodeHost(
                            windowState = windowState,
                            onBackPressedEvents = events.receiveAsFlow().mapNotNull {
                                if (it is Events.OnBackPressed) Unit else null
                            }
                        ) { buildContext ->
                            RootNode(buildContext)
                        }
                    }
                }
            }
        }
    }
}
