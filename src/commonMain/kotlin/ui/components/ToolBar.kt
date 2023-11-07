package ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

/**
 * ToolBar
 * @author Челноков Алексей (Shizik-tech)
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolBar() {
    val icons = listOf(
        Icons.Default.Add,
        Icons.Default.Menu,
        Icons.Default.List,
        Icons.Default.AccountBox
    )

    Row(
        modifier = Modifier
            .padding(56.dp)
            .background(MaterialTheme.colorScheme.surfaceColorAtElevation(1.dp))
    ) {
        IconList(icons)
    }
}

@Composable
fun IconList(icons: List<ImageVector>) {
        Row(
            modifier = Modifier.horizontalScroll(rememberScrollState())
        ) {
            icons.forEach { icon ->
                IconButton(onClick = { /* Handle click here */ }) {
                    Icon(icon, contentDescription = null)
                }
            }
        }
    }







