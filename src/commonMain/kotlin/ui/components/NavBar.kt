package ui.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Folder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import ui.theme.MultimarkAppTheme

/**
 * Панель навигации (Navbar)
 * @author Белоцерковский Марат (MIAPROT)
 * @author Сергей Рейнн (bulkabuka)
 * @param directory Путь к файлу
 */
@Composable
//TODO Заменить параметр по умолчанию
fun NavBar(directory: String = "D:\\Battle.net\\Battle.net.13521\\audio") {
    val pathList = directory.split("\\", "/")
    val file = pathList.last()
    val search = remember { mutableStateOf("") }

    BoxWithConstraints(Modifier.fillMaxWidth()) {
        val width = this.maxWidth
        Row(
            modifier = Modifier.height(80.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically
        ) {
            LogoTitle(Modifier.size(80.dp), false)
            Row(
                Modifier.shadow(4.dp, shape = RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.primaryContainer, shape = RoundedCornerShape(16.dp))
                    .fillMaxHeight()
                    .weight(10f).padding(vertical = 8.dp, horizontal = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Icon(Icons.Outlined.Folder, null)
                Text(
                    text = pathList.subList(0, pathList.lastIndex).joinToString("  |  "),
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(text = "  |  ", style = MaterialTheme.typography.bodyLarge)
                Icon(Icons.Default.Book, null)
                Text(text = file, style = MaterialTheme.typography.bodyLarge)

            }
            if (width > 900.dp) {
                Row(
                    modifier = Modifier.weight(6f).shadow(4.dp, shape = RoundedCornerShape(16.dp)).fillMaxHeight()
                        .background(MaterialTheme.colorScheme.tertiaryContainer, shape = RoundedCornerShape(16.dp))
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    SearchBar(search.value, { search.value = it }, Modifier.weight(4f))
                    Image(
                        Icons.Default.Settings,
                        contentDescription = "Settings gear", modifier = Modifier.size(30.dp),
                    )
                    Image(
                        Icons.Default.Palette,
                        contentDescription = "Preview customization palette", modifier = Modifier.size(30.dp),
                    )
                }
            }
        }
    }

}

@Preview
@Composable
fun PreviewNavBar() {
    MultimarkAppTheme {
        Surface(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
            NavBar()
        }
    }
}