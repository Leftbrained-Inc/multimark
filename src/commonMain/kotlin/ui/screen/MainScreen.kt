package ui.screen

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.*
import androidx.compose.ui.unit.dp
import kotlinx.io.files.Path
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf
import ui.components.MarkdownField
import ui.components.NavBar
import viewmodel.FileSaveViewModel

/**
 * Экран редактирования Markdown-файла
 * @property startedText Текст файла
 * @author Сергей Рейнн (bulkabuka)
 */
@Composable
fun MainScreen(path: Path) {

    val viewModel: FileSaveViewModel = koinInject<FileSaveViewModel> { parametersOf(path) }




    Surface(color = Color.White) {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(Modifier.padding(12.dp)) {
                NavBar(path, viewModel.isSaved)
            }
            Row(Modifier.padding(12.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                MarkdownField(
                    {
                        viewModel.text = it
                    }, viewModel.text, modifier = Modifier.fillMaxSize().onPreviewKeyEvent {
                        when {
                            (it.isCtrlPressed && it.key == Key.S) -> {
                                viewModel.saveFile()
                                true
                            }

                            else -> false
                        }
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun MainScreenPPreview() {
    MainScreen(Path(""))
}