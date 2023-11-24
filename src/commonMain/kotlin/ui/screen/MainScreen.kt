package ui.screen

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import core.extensions.MarkdownPreview
import core.extensions.markdownToHtml
import models.TreeDTO
import ui.components.MarkdownField
import ui.components.NavBar
import ui.components.Tree

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
                    val html by remember { derivedStateOf { markdownToHtml(text) } }
                    LaunchedEffect(html) {
                        println(html)
                    }
                    MarkdownPreview(html)
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