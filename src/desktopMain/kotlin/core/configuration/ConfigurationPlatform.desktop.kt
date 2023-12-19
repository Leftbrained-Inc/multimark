package core.configuration

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.type
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
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
 * @author Василий Панков (pank-su)
 */
actual abstract class ConfigurationPlatform actual constructor() : Configuration() {

    val windows = mutableStateListOf<WindowState>(this.window.state)


    /**
     * Отображение экрана и навигации
     * @author Василий Панков (pank-su)
     */
    actual open fun render() {
        application {
            content {
                val configuration = LocalConfiguration.current
                for (window in windows) {
                    Window(
                        this::exitApplication,
                        state = window,
                        onKeyEvent = { keyEvent ->
                            if (keyEvent.type == KeyEventType.KeyUp)
                                configuration.keyMap.shorts.forEach {
                                    if (it.condition(keyEvent)) {
                                        it.action()
                                        return@Window true
                                    }
                                }
                            false

                        },
                        icon = configuration.window.icon,
                        title = configuration.window.title
                    ) {
                        it {
                            DesktopNodeHost(
                                windowState = configuration.window.state,
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
}
