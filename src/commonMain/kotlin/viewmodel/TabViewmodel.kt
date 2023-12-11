package viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.Density
import core.configuration.Configuration
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ui.components.TabCategory

/**
 * viewModel вкладок
 * @property tabs Вкладки
 * @see TabCategory
 * @author Сергей Рейнн (bulkabuka)
 * @author Василий Панков (pank-su)
 */

class TabViewmodel {
    val tabs = mutableStateListOf<TabCategory>()
    private var _selectedTabIndex = MutableStateFlow(0)
    var selectedTabIndex = _selectedTabIndex.asStateFlow()

    // Минимальный offset чтобы выйти из навигации
    val minimumOffsetToMoveOutNav = Offset(0f, 10f)

    suspend fun select(tabIndex: Int) {
        _selectedTabIndex.emit(tabIndex)
    }

    fun calculateNewPosition(tab: TabCategory) {
        TODO("РАСЧИТАТЬ НОВОЕ ПОЛОЖЕНИЕ ТАБА")
    }


}

expect fun TabViewmodel.onDragEnd(tab: TabCategory, configuration: Configuration, density: Density)