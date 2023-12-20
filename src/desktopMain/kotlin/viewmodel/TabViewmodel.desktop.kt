package viewmodel

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.Density
import core.configuration.ConfigurationImpl
import core.extensions.window.isOutWindow
import core.extensions.window.onOutWindow
import core.extensions.window.window
import ui.components.tabs.TabCategory

/**
 * @author Василий Панков (pank-su)
 */
actual fun TabViewmodel.onDragEnd(
    tab: TabCategory,
    configuration: ConfigurationImpl,
    density: Density
) {
    val dragTabState = tab.dragTabState
    val positionNow =
        Pair(dragTabState.position.first + dragTabState.offset.x, dragTabState.position.second + dragTabState.offset.y)
    if (configuration.window.isOutWindow(positionNow, density)) {
        tab.onOutWindow(configuration, density)
        tab.dragTabState.offset = Offset.Zero
        tabs.remove(tab)
        return
    }
    println(dragTabState.offset)
    if (dragTabState.offset.y < this.minimumOffsetToMoveOutNav.y) {
        this.calculateNewPosition(tab)
        tab.dragTabState.offset = Offset.Zero
        return
    }

}