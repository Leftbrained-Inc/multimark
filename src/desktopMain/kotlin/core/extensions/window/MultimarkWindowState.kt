package core.extensions.window

import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import di.appModule
import org.koin.core.KoinApplication
import ui.components.tabs.TabCategory

/**
 * Собственное состояние окна\
 *
 * @param tabs табы которые необходимо открыть в новом окне
 *
 * @see TabCategory
 * @see WindowState
 * @author Василий Панков (pank-su)
 */
class MultimarkWindowStateImpl(
    var tabs: List<TabCategory>,
    var koinApplication: KoinApplication,
    override var isMinimized: Boolean,
    override var placement: WindowPlacement,
    override var position: WindowPosition,
    override var size: DpSize
) : WindowState

/**
 * Функция инициализатор класса [MultimarkWindowStateImpl]
 *
 * @see MultimarkWindowStateImpl
 * @author Василий Панков (pank-su)
 */
fun MultimarkWindowState(
    koinApplication: KoinApplication = org.koin.dsl.koinApplication { modules(appModule()) },
    tabs: List<TabCategory> = listOf(),
    isMinimized: Boolean = false,
    placement: WindowPlacement = WindowPlacement.Floating,
    position: WindowPosition = WindowPosition(
    Alignment.Center
), size: DpSize = DpSize.Unspecified): MultimarkWindowStateImpl = MultimarkWindowStateImpl(
    tabs, koinApplication, isMinimized, placement, position, size
)