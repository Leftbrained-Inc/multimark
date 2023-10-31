package ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import core.configuration.LocalConfiguration
import models.FileDTO
import ui.components.FileList
import ui.components.LogoTitle
import ui.components.SearchBar
import ui.utils.dp
import java.util.*

/**
 * Стартовый экран
 *
 * @author Белоцерковский Марат (MIAPROT)
 * @author Сергей Рейнн (bulkabuka)
 * @author Панков Вася (pank-su)
 */
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun LaunchScreen() {
    val config = LocalConfiguration.current
    val windowSizeClass = calculateWindowSizeClass()
    var basicFont by remember {
        mutableStateOf(30.sp)
    }
    var logoSize by remember {
        mutableStateOf(72.dp)
    }
    val cardList = remember {
        mutableStateListOf(FileDTO("Test1", Date()), FileDTO("Test 2", Date()))
    }

    LaunchedEffect(windowSizeClass) {
        basicFont = when (windowSizeClass.widthSizeClass) {
            WindowWidthSizeClass.Compact -> 16.sp
            WindowWidthSizeClass.Medium -> 18.sp
            WindowWidthSizeClass.Expanded -> 20.sp
            else -> 10.sp
        }
        logoSize = when (windowSizeClass.widthSizeClass) {
            WindowWidthSizeClass.Compact -> 48.dp
            WindowWidthSizeClass.Medium -> 64.dp
            WindowWidthSizeClass.Expanded -> 72.dp
            else -> 10.dp
        }
    }

    Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        Column(
            Modifier.widthIn(200.dp, 600.dp).align(Alignment.TopCenter),
            verticalArrangement = Arrangement.spacedBy(12.dp, alignment = Alignment.CenterVertically)
        ) {
            LogoTitle(Modifier.fillMaxWidth())
            SearchBar(Modifier.height(100.dp))
            // Список недавно просмотренных
            Column(modifier = Modifier.weight(6f, false), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    "Last Viewed",
                    style = MaterialTheme.typography.headlineMedium,
                )
                FileList(cardList, modifier = Modifier.padding(top = 24.dp))
            }
        }
        Text(
            "Crafted with ❤\uFE0F in Leftbrained",
            modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 24.dp),
            fontWeight = FontWeight(500),
        )
    }

}
