package viewmodel

import androidx.compose.runtime.mutableStateListOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ui.components.TabCategory

class TabViewmodel {
    val tabs = mutableStateListOf<TabCategory>()
    private var _selectedTabIndex = MutableStateFlow(0)
    var selectedTabIndex = _selectedTabIndex.asStateFlow()

    suspend fun select(tabIndex: Int) {
        _selectedTabIndex.emit(tabIndex)
    }
}