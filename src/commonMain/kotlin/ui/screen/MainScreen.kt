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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.*
import androidx.compose.ui.unit.dp
import kotlinx.io.buffered
import kotlinx.io.files.Path
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf
import ui.components.MarkdownField
import kotlinx.io.files.SystemFileSystem
import kotlinx.io.readString
import org.koin.compose.koinInject
import ui.components.NavBar
import viewmodel.FileSaveViewModel
import viewmodel.TabViewmodel

/**
 * Экран редактирования Markdown-файла
 * @param path Путь к файлу
 * @author Сергей Рейнн (bulkabuka)
 */
@Composable
fun MainScreen(path: Path) {

    val viewModel: FileSaveViewModel = koinInject<FileSaveViewModel> { parametersOf(path) }


    Surface(color = Color.White) {
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