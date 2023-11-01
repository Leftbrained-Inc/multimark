package ui.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.bonsai.core.Bonsai
import cafe.adriel.bonsai.core.node.Branch
import cafe.adriel.bonsai.core.node.Leaf
import cafe.adriel.bonsai.core.tree.Tree
import org.jetbrains.exposed.sql.Min

// Небольшая разметка дерева файлов
@Composable
fun ExpandableText(text: String) {
    var isExpanded by remember { mutableStateOf(false) }
    var isThirdLevelExpanded by remember { mutableStateOf(false) }

    Text(
        text = text,
        modifier = Modifier
            .height(20.dp)
            .padding(start = 5.dp)
            .clickable { isExpanded = !isExpanded }
       // fontWeight = if (isExpanded) FontWeight.Normal else FontWeight.Bold,
    )
        // то как работает открытие вкладок
    if (isExpanded) {
        Text(
            text = "Второй уровень...",
            modifier = Modifier
                .padding(start = 20.dp)
                .clickable {
                    isThirdLevelExpanded = !isThirdLevelExpanded
                }
        )

        if (isThirdLevelExpanded) {
            Text(
                text = "Третий уровень...",
                modifier = Modifier
                    .padding(start = 40.dp)
                    .clickable {
                        // Разворачивается дальше
                    }
            )
        }
    }
}

@Composable
fun BonsaiExample() {
    LazyColumn {
        // Пример работы
        items(3) {
            ExpandableText("Первый уровень...")
        }
    }
}
@Preview
@Composable
fun PrewBonsaiExample(){
    BonsaiExample()
}
