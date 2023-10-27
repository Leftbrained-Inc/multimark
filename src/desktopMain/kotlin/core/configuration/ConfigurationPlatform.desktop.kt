package core.configuration

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.rememberWindowState
import com.bumble.appyx.navigation.integration.DesktopNodeHost
import core.shortcut.shorts
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

        Window(onCloseRequest, onKeyEvent = { keyEvent ->
            shorts.forEach {
                if (it.condition(keyEvent)) {
                    it.action()
                    return@Window true
                }
            }
            false
        }) {
            CompositionLocalProvider(LocalConfiguration provides this@ConfigurationPlatform as ConfigurationImpl) {
                val config = LocalConfiguration.current
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
