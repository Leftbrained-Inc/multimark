package core.extensions.window

import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState

class MultimarkWindowStateImpl(
    override var isMinimized: Boolean,
    override var placement: WindowPlacement,
    override var position: WindowPosition,
    override var size: DpSize
) : WindowState