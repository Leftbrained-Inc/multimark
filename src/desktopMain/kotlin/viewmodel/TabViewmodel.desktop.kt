package viewmodel

import androidx.compose.ui.unit.Density
import core.configuration.Configuration
import core.extensions.isOutWindow
import core.extensions.onOutWindow
import core.extensions.window
import ui.components.TabCategory

/**
 * @author Василий Панков (pank-su)
 */
actual fun TabViewmodel.onDragEnd(
    tab: TabCategory,
    configuration: Configuration,
    density: Density
) {
    val dragTabState = tab.dragTabState
    val positionNow =
        Pair(dragTabState.position.first + dragTabState.offset.x, dragTabState.position.second + dragTabState.offset.y)
    if (configuration.window.isOutWindow(positionNow, density)) {
        tab.onOutWindow()
        return
    }
    println(dragTabState.offset)
    if (dragTabState.offset.y < this.minimumOffsetToMoveOutNav.y) {
        this.calculateNewPosition(tab)
        return
    }

}