package ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import core.configuration.LocalConfiguration
import core.extensions.window
import ui.utils.dp
import ui.utils.dps

/**
 * Функция для отображения вкладки во время перетаскивания
 * @param draggedTab Перетаскиваемая вкладка
 * @see TabCategory
 * @author Василий Панков (pank-su)
 */
@Composable
actual fun onDragTab(draggedTab: TabCategory) {
    val density = LocalDensity.current
    val configuration = LocalConfiguration.current
    val defaultWindowPosition = WindowPosition(configuration.window.state.position.x +
            with(density) { draggedTab.dragTabState.position.first.toDp() - 225.dp },
        configuration.window.state.position.y + with(density) { draggedTab.dragTabState.position.second.toDp() } + 25.dps)
    val windowState = rememberWindowState(
        WindowPlacement.Floating,
        size = DpSize(height = 450.dp, width = 500.dp),
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
        Column(modifier = Modifier.width(500.dp).height(450.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Tab(
                draggedTab,
                true,
                onClick = {},
                modifier = Modifier.width(with(density) { draggedTab.size.toSize().toDpSize() }.width)
            )
            Spacer(Modifier.height(12.dp))
            Box(modifier = Modifier.clip(RoundedCornerShape(12.dp)).graphicsLayer {
                alpha = 0.8f
            }.background(MaterialTheme.colorScheme.background).fillMaxWidth().height(400.dp)) {
                draggedTab.screen(draggedTab)
            }
        }
    }
}