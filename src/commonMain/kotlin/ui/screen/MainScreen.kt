package ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bumble.appyx.components.backstack.BackStack
import com.bumble.appyx.components.backstack.operation.pop
import com.bumble.appyx.components.backstack.operation.push
import navigation.NavTarget
import org.koin.compose.koinInject
import ui.components.NavBar
import ui.components.onDragTab
import ui.components.tabs.TabCategory
import viewmodel.TabViewModel

/**
 * Экран редактирования Markdown-файла
 * @author Сергей Рейнн (bulkabuka)
 */
@Composable
fun MainScreen(backStack: BackStack<NavTarget>) {
    val tabViewModel: TabViewModel = koinInject()
    val selectedTabIndex by tabViewModel.selectedTabIndex.collectAsState()
    val draggedTab by remember {
        derivedStateOf {
            tabViewModel.tabs.firstOrNull { it.dragTabState.isDrag }
        }
    }

    if (draggedTab != null) {
        onDragTab(draggedTab!!)
    }
    Surface(color = Color.White) {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(Modifier.padding(12.dp)) {
                NavBar()
            }
            Row(Modifier.padding(12.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {

                val tab = tabViewModel.tabs.getOrElse(selectedTabIndex) {
                    tabViewModel.tabs.firstOrNull() ?: TabCategory.Empty()
                }
                tab.screen(tab)
                LaunchedEffect(tabViewModel.tabs.firstOrNull()) {
                    if (tabViewModel.tabs.firstOrNull() == null) {
                        backStack.pop()
                        if (backStack.elements.value.all.size == 1) {
                            backStack.push(NavTarget.LaunchScreen) // TODO закрывать окно если в нём нет табов
                        }
                    }
                }
            }
        }
    }
}