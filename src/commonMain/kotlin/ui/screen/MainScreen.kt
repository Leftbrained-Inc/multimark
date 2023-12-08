package ui.screen

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.io.files.Path
import org.koin.compose.koinInject
import ui.components.NavBar
import viewmodel.TabViewmodel

/**
 * Экран редактирования Markdown-файла
 * @param path Путь к файлу
 * @author Сергей Рейнн (bulkabuka)
 */
@Composable
fun MainScreen(path: Path) {

    val tabViewmodel: TabViewmodel = koinInject()
    val selectedTabIndex by tabViewmodel.selectedTabIndex.collectAsState()


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
    MainScreen(Path(""))
}