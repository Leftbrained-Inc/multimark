package ui.screen

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.multiplatform.webview.web.*
import dev.datlag.kcef.KCEF
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import models.TreeDTO
import ui.components.MarkdownField
import ui.components.NavBar
import ui.components.Tree
import java.io.File
import kotlin.math.max

/**
 * Экран редактирования Markdown-файла
 * @property text Текст файла
 * @author Сергей Рейнн (bulkabuka)
 */
@Composable
fun MainScreen() {
    var text by remember { mutableStateOf("") }
    Surface(color = Color.White) {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(Modifier.padding(12.dp)) {
                NavBar("root/users/sara/desktop/directory/first")
            }
            Row(Modifier.padding(12.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Column(
                    Modifier.background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(10.dp))
                        .padding(12.dp)
                ) {
                    Tree(
                        tree = TreeDTO(
                            name = "root", treeList = listOf(
                                TreeDTO(
                                    name = "child1", treeList = listOf(
                                        TreeDTO(
                                            name = "child2", treeList = listOf(
                                                TreeDTO(name = "child3", treeList = listOf())
                                            )
                                        )
                                    )
                                )
                            )
                        ), Modifier.fillMaxHeight().width(150.dp)
                    )
                }
                Column(
                    Modifier.background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(10.dp))
                        .padding(12.dp)
                ) {
                    MarkdownField(
                        { text = it }, text, modifier = Modifier.fillMaxHeight().width(400.dp)
                    )
                }
                Column(Modifier.background(Color.White, shape = RoundedCornerShape(10.dp))) {
                    // TODO перенести в натив

                    var restartRequired by remember { mutableStateOf(false) }
                    var downloading by remember { mutableStateOf(0F) }
                    var initialized by remember { mutableStateOf(false) }

                    LaunchedEffect(Unit) {
                        withContext(Dispatchers.IO) {
                            KCEF.init(builder = {
                                installDir(File("kcef-bundle"))
                                progress {
                                    onDownloading {
                                        downloading = max(it, 0F)
                                    }
                                    onInitialized {
                                        initialized = true
                                    }
                                }
                                settings {
                                    cachePath = File("cache").absolutePath
                                }
                            }, onError = {
                                it?.printStackTrace()
                            }, onRestartRequired = {
                                restartRequired = true
                            })
                        }
                    }
                    val state = rememberWebViewState("https://github.com/Leftbrained-Inc/multimark")


                    if (restartRequired) {
                        Text(text = "Restart required.")
                    } else {
                        if (initialized) {
                            WebView(state, modifier = Modifier.fillMaxSize())
                        } else {
                            Text(text = "Downloading $downloading%")
                        }
                    }

                    DisposableEffect(Unit) {
                        onDispose {
                            KCEF.disposeBlocking()
                        }
                    }

                }
            }
        }
    }
}

@Preview
@Composable
fun MainScreenPPreview() {
    MainScreen()
}