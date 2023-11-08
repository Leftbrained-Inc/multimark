package core.configuration

import androidx.compose.runtime.remember
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.bumble.appyx.navigation.integration.DesktopNodeHost
import core.extensions.keyMap
import core.extensions.window
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.receiveAsFlow
import navigation.RootNode


sealed class Events {
    object OnBackPressed : Events()
}


/**
 * Нативная реализация
 *
 * @author Панков Вася (pank-su)
 */
actual abstract class ConfigurationPlatform actual constructor() : core.configuration.Configuration() {

    /**
     * Отображение экрана и навигации
     * @author Панков Вася (pank-su)
     */
    actual open fun render() {
        application {
            val events: Channel<Events> = Channel()
            val windowState = rememberWindowState(size = DpSize(480.dp, 658.dp))
            val eventScope = remember { CoroutineScope(SupervisorJob() + Dispatchers.Main) }
            content {
                val configuration = LocalConfiguration.current
                Window(this::exitApplication, onKeyEvent = { keyEvent ->
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
