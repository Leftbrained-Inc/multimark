package ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Circle
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
import kotlinx.io.files.Path
import ui.theme.MultimarkAppTheme
import ui.utils.dp

/**
 * Панель навигации (Navbar)
 * @author Марат Белоцерковский (MIAPROT)
 * @author Сергей Рейнн (bulkabuka)
 * @param path Путь к файлу
 * @param isSaved Сохранен ли файл
 */
@Composable
fun NavBar(path: Path, isSaved: Boolean) {
    val pathList = buildList {
        var parent = path
        while (parent.parent != null) {
            add(parent.name)
            parent = parent.parent!!
        }
        reverse()
    }
    val file = pathList.last()
    val search = remember { mutableStateOf("") }

    BoxWithConstraints(Modifier.fillMaxWidth()) {
        val width = this.maxWidth
        Row(
            modifier = Modifier.height(80.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically
        ) {
            LogoTitle(Modifier.size(64.dp), false)
            Row(
                Modifier.shadow(4.dp, shape = RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant, shape = RoundedCornerShape(16.dp))
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

                AnimatedVisibility(!isSaved) {
                    Icon(Icons.Default.Circle, null)
                }

            }
            if (width > 900.dp) {
                Row(
                    modifier = Modifier.weight(6f).shadow(4.dp, shape = RoundedCornerShape(16.dp)).fillMaxHeight()
                        .background(MaterialTheme.colorScheme.tertiaryContainer, shape = RoundedCornerShape(16.dp))
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    SearchBar(search.value, { search.value = it }, Modifier.weight(4f))
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Settings",
                        modifier = Modifier.size(30.dp),
                        tint = MaterialTheme.colorScheme.onTertiaryContainer
                    )
                    Icon(
                        Icons.Default.Palette,
                        contentDescription = "Preview customization palette", modifier = Modifier.size(30.dp),
                        tint = MaterialTheme.colorScheme.onTertiaryContainer
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
            NavBar(Path(""), false)
        }
    }
}