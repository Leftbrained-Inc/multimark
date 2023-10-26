package ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import core.dsl.elements.configuration.LocalConfiguration

/**
 * Элемент логотипа
 * @param modifier установка размеров
 * @author Белоцерковский Марат (MIAPROT)
 */
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun LogoTitle(modifier: Modifier){


    val windowSizeClass = calculateWindowSizeClass()
    val config = LocalConfiguration.current
    var titleFontSize by remember {
        mutableStateOf(30.sp)
    }
    var logoSize by remember {
        mutableStateOf(150.dp)
    }
    LaunchedEffect(windowSizeClass) {

        titleFontSize = when (windowSizeClass.widthSizeClass) {
            WindowWidthSizeClass.Compact -> 30.sp
            WindowWidthSizeClass.Medium -> 40.sp
            WindowWidthSizeClass.Expanded -> 60.sp
            else -> 10.sp
        }
        logoSize = when (windowSizeClass.widthSizeClass) {
            WindowWidthSizeClass.Compact -> 100.dp
            WindowWidthSizeClass.Medium -> 125.dp
            WindowWidthSizeClass.Expanded -> 150.dp
            else -> 10.dp
        }
    }


    Row(modifier, verticalAlignment = Alignment.CenterVertically) {
        config.icon(androidx.compose.ui.Modifier.size(logoSize))
        Text(text = "Multimark", fontSize = titleFontSize)
    }
}
