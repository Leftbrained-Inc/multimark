package ui.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.ClickableText

import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.bonsai.core.Bonsai
import cafe.adriel.bonsai.core.node.Branch
import cafe.adriel.bonsai.core.node.Leaf
import models.FileDTO
import models.TreeDTO
import org.jetbrains.exposed.sql.Min
import java.io.File
import java.nio.file.Files

@Composable
fun TreeElement(tree: TreeDTO, modifier: Modifier = Modifier) {
    var isShow by remember { mutableStateOf(false) }

    Column {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable { isShow = !isShow }) {
            Text(tree.name, modifier = modifier)
            if (!isShow) Icon(Icons.Default.KeyboardArrowRight, contentDescription = null)
            else
                Icon(Icons.Default.KeyboardArrowDown, contentDescription = null)
        }
        if (tree.treeList.isNotEmpty() && isShow) {
            tree.treeList.forEach {
                TreeElement(it, modifier.padding(start = 20.dp))
            }
        }
    }
}

@Composable
fun Tree() {
    val tree =
        TreeDTO("Первый", listOf(TreeDTO("Второй", listOf(TreeDTO("Test", listOf()))), TreeDTO("Третий", listOf())))
    Column {
        Text(text = "File Tree", modifier = Modifier.padding(bottom = 10.dp), style = MaterialTheme.typography.headlineMedium)
        TreeElement(tree)
    }
}

@Preview
@Composable
fun PrewBonsaiExample() {
    Tree()
}
