package ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import models.TreeDTO

/**
 * Элемент дерева файлов
 * @param tree Вложенное дерево файлов
 * @param modifier Modifier
 * @see TreeDTO
 * @see Tree
 */
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

/**
 * Компонент списка файлов с их вложенностью
 * @param tree Дерево файлов
 * @param modifier Modifier
 * @see TreeDTO
 * @see TreeElement
 */
@Composable
fun Tree(tree: TreeDTO, modifier: Modifier) {
    Column(modifier = modifier) {
        Text(
            text = "File Tree",
            modifier = Modifier.padding(bottom = 10.dp),
            style = MaterialTheme.typography.headlineMedium
        )
        TreeElement(tree)
    }
}