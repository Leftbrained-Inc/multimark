package ui.screen

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import org.koin.compose.koinInject
import ui.components.NavBar
import ui.components.onDragTab
import viewmodel.TabViewmodel

/**
 * Экран редактирования Markdown-файла
 * @author Сергей Рейнн (bulkabuka)
 */
@Composable
fun MainScreen() {

    val tabViewmodel: TabViewmodel = koinInject()
    val selectedTabIndex by tabViewmodel.selectedTabIndex.collectAsState()
    val draggedTab by remember {
        derivedStateOf {
            tabViewmodel.tabs.firstOrNull { it.dragTabState.isDrag }
        }
    }

    val density = LocalDensity.current


    if (draggedTab != null) {
        onDragTab(draggedTab!!)
    }
    Surface(color = Color.White) {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(Modifier.padding(12.dp)) {
                NavBar()
            }
            Row(Modifier.padding(12.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                val tab = tabViewmodel.tabs[selectedTabIndex]
                tab.screen(tab)
            }
        }
    }
}

@Preview
@Composable
fun MainScreenPPreview() {
    MainScreen()
}