package ui.components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset

/**
 * Состояние для DragTab
 * @property isDrag флаг перетаскивания
 * @property position позиция DragTab
 * @property offset смещение DragTab
 * @author Василий Панков (pank-su)
 */
class DragTabState {
    var isDrag by mutableStateOf(false)
    var position by mutableStateOf(Pair(0f, 0f))
    var offset: Offset by mutableStateOf(Offset.Zero)
}
