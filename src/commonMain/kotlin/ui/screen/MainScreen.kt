package ui.screen

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.isCtrlPressed
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.unit.dp
import kotlinx.io.buffered
import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem
import kotlinx.io.readString
import kotlinx.io.writeString
import ui.components.MarkdownField
import ui.components.NavBar

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

    var text by remember { mutableStateOf(contentSaved) }
    val isSaved by remember(text, contentSaved, saveNow) {  derivedStateOf {
        text == contentSaved
    }}
    Surface(color = MaterialTheme.colorScheme.background) {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(Modifier.padding(12.dp)) {
                NavBar(path, isSaved)
            }
            Row(Modifier.padding(12.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                MarkdownField(
                    { text = it
                    }, text, modifier = Modifier.fillMaxSize().onPreviewKeyEvent {
                        when {
                            (it.isCtrlPressed && it.key == Key.S) -> {
                                val sink = SystemFileSystem.sink(path).buffered()

                                sink.writeString(text)
                                sink.close()
                                saveNow = true
                                saveNow = false
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