package core.configuration

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.type
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import com.bumble.appyx.navigation.integration.DesktopNodeHost
import core.extensions.window.MultimarkWindowStateImpl
import core.extensions.window.window
import core.shortcut.keyMap
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.receiveAsFlow
import navigation.NavTarget
import navigation.RootNode
import org.koin.compose.koinInject
import viewmodel.TabViewModel


sealed class Events {
    data object OnBackPressed : Events()
}

val events: Channel<Events> = Channel()

// Список окон
val windows = mutableStateListOf<MultimarkWindowStateImpl>()

// Локальное состояние для каждого окна
val LocalWindowState = compositionLocalOf {
    WindowState()
}

/**
 * Нативная реализация
 * @author Василий Панков (pank-su)
 */
actual abstract class ConfigurationPlatform actual constructor() : Configuration() {


    /**
     * Отображение экрана и навигации
     * @author Василий Панков (pank-su)
     */
    actual open fun render() {
        // Добавление начального настраиваемого состояния окна
        windows.add(window.state)
        application {
            content {
                //  Реализациия нативных комонентов
                val configuration = LocalConfiguration.current
                for (window in windows) {
                    Window(
                        {
                            if (windows.size == 1)
                                exitApplication()
                            else windows.remove(window)
                        },
                        state = window,
                        // Обработка горячих клавиш
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
                        CompositionLocalProvider(LocalWindowState provides window) {
                            it {
                                val tabsViewModel = koinInject<TabViewModel>()
                                LaunchedEffect(Unit){
                                    tabsViewModel.tabs.addAll(window.tabs)
                                }
                                // Appyx navigation
                                DesktopNodeHost(
                                    windowState = configuration.window.state,
                                    onBackPressedEvents = events.receiveAsFlow().mapNotNull {
                                        if (it is Events.OnBackPressed) Unit else null
                                    }
                                ) { buildContext ->

                                    RootNode(buildContext, initialTarget = if (window.tabs.isEmpty()) NavTarget.LaunchScreen else NavTarget.MainScreen)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
