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
import androidx.compose.ui.unit.dp
import core.dsl.elements.configuration.LocalConfiguration

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
fun LogoTitle(modifier: Modifier) {
    val windowSizeClass = calculateWindowSizeClass()
    val config = LocalConfiguration.current
    var logoSize by remember {
        mutableStateOf(48.dp)
    }

    LaunchedEffect(windowSizeClass) {
        logoSize = when (windowSizeClass.widthSizeClass) {
            WindowWidthSizeClass.Compact -> 48.dp
            WindowWidthSizeClass.Medium -> 64.dp
            WindowWidthSizeClass.Expanded -> 72.dp
            else -> 24.dp
        }
    }

    Row(
        modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp, alignment = Alignment.CenterHorizontally)
    ) {
        config.icon(Modifier.size(logoSize))
        Text(text = "Multimark", style = MaterialTheme.typography.headlineMedium)
    }
}
