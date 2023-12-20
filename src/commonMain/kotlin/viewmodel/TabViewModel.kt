package viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.Density
import core.configuration.ConfigurationImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ui.components.tabs.TabCategory

/**
 * ViewModel вкладок
 * @property tabs Вкладки
 * @property selectedTabIndex Индекс выбранной вкладки
 * @see TabCategory
 * @author Сергей Рейнн (bulkabuka)
 * @author Василий Панков (pank-su)
 */
class TabViewModel {
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
     */
    fun calculateNewPosition(tab: TabCategory) {
        var nearTabIndex =
            tabs.indexOfLast { tab.dragTabState.position.first + tab.dragTabState.offset.x > it.dragTabState.position.first && tab !== it }
        if (nearTabIndex == -1) {
            nearTabIndex = 0
        }
        val selectedTab = tabs[selectedTabIndex.value]
        tabs.remove(tab)
        tabs.add(nearTabIndex, tab)
        CoroutineScope(Dispatchers.Default).launch {
            select(
                tabs.indexOf(selectedTab)
            )
        }
    }
}

expect fun TabViewModel.onDragEnd(tab: TabCategory, configuration: ConfigurationImpl, density: Density)
