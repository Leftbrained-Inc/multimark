package ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import core.configuration.LocalConfiguration
import ui.utils.dp

/**
 * Элемент логотипа
 * @param modifier Установка размеров
 *
 * @author Белоцерковский Марат (MIAPROT)
 * @author Сергей Рейнн (bulkabuka)
 * @author Панков Вася (pank-su)
 */
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun LogoTitle(modifier: Modifier, showTitle: Boolean) {
    val windowSizeClass = calculateWindowSizeClass()
    val config = LocalConfiguration.current
    var logoSize by remember {
        mutableStateOf(64.dp)
    }

    LaunchedEffect(windowSizeClass) {
        logoSize = when (windowSizeClass.widthSizeClass) {
            WindowWidthSizeClass.Compact -> 64.dp
            WindowWidthSizeClass.Medium -> 72.dp
            WindowWidthSizeClass.Expanded -> 86.dp
            else -> 64.dp
        }
    }

    Row(
        modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp, alignment = Alignment.CenterHorizontally)
    ) {
        config.logo(Modifier.size(logoSize))
        if (showTitle) {
            Text(text = "Multimark", style = MaterialTheme.typography.headlineMedium)
        }
    }
}
