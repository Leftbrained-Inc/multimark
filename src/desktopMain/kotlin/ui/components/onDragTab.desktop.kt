package ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import core.configuration.LocalConfiguration
import core.extensions.window

@Composable
actual fun onDragTab(draggedTab: TabCategory) {
    val density = LocalDensity.current
    val configuration = LocalConfiguration.current
    val defaultWindowPosition = WindowPosition(configuration.window.state.position.x +
            with(density) { draggedTab.dragTabState.position.first.toDp() },
        configuration.window.state.position.y + with(density) { draggedTab.dragTabState.position.second.toDp() })
    val windowState = rememberWindowState(
        WindowPlacement.Floating,
        size = with(density) { draggedTab.size.toSize().toDpSize() },
        position = defaultWindowPosition
    )
    LaunchedEffect(draggedTab.dragTabState.offset) {
        windowState.position =
            WindowPosition(defaultWindowPosition.x + with(density) { draggedTab.dragTabState.offset.x.toDp() },
                defaultWindowPosition.y + with(density) { draggedTab.dragTabState.offset.y.toDp() })
    }
    Window(
        onCloseRequest = { draggedTab.dragTabState.isDrag = false },
        transparent = true,
        undecorated = true,
        resizable = false,
        state = windowState
    ) {
        Tab(
            draggedTab,
            true,
            onClick = {},
            modifier = Modifier.size(with(density) { draggedTab.size.toSize().toDpSize() })
        )
    }
}