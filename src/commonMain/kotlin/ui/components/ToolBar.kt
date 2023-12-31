package ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

/**
 * Элемент ToolBar
 * @property icon Иконка кнопки
 * @property onClick Действие при нажатии
 * @author Алексей Челноков (shizik-tech)
 */
data class ToolBarItem(val icon: ImageVector, val onClick: () -> Unit)

/**
 * ToolBar
 * @param icons Список иконок и действий при нажатии оных
 * @author Алексей Челноков (shizik-tech)
 */
@Composable
fun ToolBar(
    icons: List<ToolBarItem> = listOf(
        ToolBarItem(Icons.Default.Menu) { println("Test") }
    )
) {
    Row(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surfaceColorAtElevation(1.dp))
            .horizontalScroll(rememberScrollState())
    ) {
        icons.forEach { toolBarItem ->
            IconButton(onClick = { toolBarItem.onClick() }) {
                Icon(toolBarItem.icon, contentDescription = null)
            }
        }
    }
}