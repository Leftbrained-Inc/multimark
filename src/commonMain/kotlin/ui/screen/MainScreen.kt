package ui.screen

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.io.buffered
import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem
import kotlinx.io.readString
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

    var saveNow by remember {
        mutableStateOf(false)
    }

    val contentSaved by remember(saveNow) {
        derivedStateOf {
            val buffer = SystemFileSystem.source(path).buffered()
            val text = buffer.readString()
            buffer.close()
            text
        }
    }
    val tabViewmodel: TabViewmodel = koinInject()
    val selectedTabIndex = tabViewmodel.selectedTabIndex.collectAsState()
    var text by remember { mutableStateOf(contentSaved) }
    val isSaved by remember(text, contentSaved, saveNow) {
        derivedStateOf {
            text == contentSaved
        }
    }
    Surface(color = MaterialTheme.colorScheme.background) {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(Modifier.padding(12.dp)) {
                NavBar()
            }
            Row(Modifier.padding(12.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                tabViewmodel.tabs[selectedTabIndex.value].screen()
            }
        }
    }
}

@Preview
@Composable
fun MainScreenPPreview() {
    MainScreen(Path(""))
}