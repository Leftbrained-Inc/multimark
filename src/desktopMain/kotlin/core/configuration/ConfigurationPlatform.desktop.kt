package core.configuration

import androidx.compose.animation.AnimatedContent
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
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
import ui.utils.Scale


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
     *
     * @param onCloseRequest передаётся request на закрытие приложения
     *
     * @author Панков Вася (pank-su)
     */
    @Composable
    actual open fun render(onCloseRequest: () -> Unit) {
        val events: Channel<Events> = Channel()
        val windowState = rememberWindowState(size = DpSize(480.dp, 658.dp))
        val eventScope = remember { CoroutineScope(SupervisorJob() + Dispatchers.Main) }

        val configuration = this

        CompositionLocalProvider(LocalConfiguration provides configuration as ConfigurationImpl) {
            Window(onCloseRequest, onKeyEvent = { keyEvent ->
                configuration.keyMap.shorts.forEach {
                    if (it.condition(keyEvent)) {
                        it.action()
                        return@Window true
                    }
                }

                false
            }, icon = this.window.icon, title = this.window.title) {

                val config = LocalConfiguration.current

                LaunchedEffect(config.scale) {
                    Scale.scale = config.scale
                    Scale.fontScale = config.fontScale
                }

                AnimatedContent(Scale.scale) {
                    config.theme {
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
