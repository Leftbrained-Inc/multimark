package ui.components

import androidx.compose.runtime.Composable
import ui.components.tabs.TabCategory

@Composable
expect fun onDragTab(draggedTab: TabCategory)