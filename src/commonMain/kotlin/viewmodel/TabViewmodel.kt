package viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.Density
import core.configuration.Configuration
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ui.components.TabCategory

/**
 * ViewModel вкладок
 * @property tabs Вкладки
 * @property selectedTabIndex Индекс выбранной вкладки
 * @see TabCategory
 * @author Сергей Рейнн (bulkabuka)
 * @author Василий Панков (pank-su)
 */
class TabViewmodel {
    val tabs = mutableStateListOf<TabCategory>()
    private var _selectedTabIndex = MutableStateFlow(0)
    var selectedTabIndex = _selectedTabIndex.asStateFlow()

    // Минимальный сдвиг чтобы выйти из навигации
    val minimumOffsetToMoveOutNav = Offset(0f, 10f)

    suspend fun select(tabIndex: Int) {
        _selectedTabIndex.emit(tabIndex)
    }

    /**
     * Рассчитывает новое положение вкладки.
     * @param tab Вкладка, для которой рассчитывается новое положение.
     * @param currentPosition Текущее положение вкладки.
     * @param delta Величина перемещения вкладки.
     */
    fun calculateNewPosition(tab: TabCategory, currentPosition: Offset, delta: Offset) {
        val newPosition = Offset(currentPosition.x + delta.x, currentPosition.y + delta.y)
    }
}

expect fun TabViewmodel.onDragEnd(tab: TabCategory, configuration: Configuration, density: Density)
