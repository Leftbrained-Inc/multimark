package ui.screen

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import models.FileDTO
import ui.components.FileList
import ui.components.LogoTitle
import ui.components.SearchBar
import java.util.*

/**
 * Экран с недавних файлов
 * @author Белоцерковский Марат (MIAPROT)
 */
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun RecentScreen() {
    val windowSizeClass = calculateWindowSizeClass()
    var secondaryTitleFontSize by remember {
        mutableStateOf(30.sp)
    }
    var basicFont by remember {
        mutableStateOf(30.sp)
    }
    val cardList = remember {
        mutableStateListOf<FileDTO>(FileDTO("Test1", Date()), FileDTO("Test 2", Date()))
    }
    // Изменение шрифта при изменении экрана
    LaunchedEffect(windowSizeClass) {
        secondaryTitleFontSize = when (windowSizeClass.widthSizeClass) {
            WindowWidthSizeClass.Compact -> 20.sp
            WindowWidthSizeClass.Medium -> 30.sp
            WindowWidthSizeClass.Expanded -> 50.sp
            else -> 10.sp
        }
        basicFont = when (windowSizeClass.widthSizeClass) {
            WindowWidthSizeClass.Compact -> 16.sp
            WindowWidthSizeClass.Medium -> 18.sp
            WindowWidthSizeClass.Expanded -> 20.sp
            else -> 10.sp
        }
    }

    Box(Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier.widthIn(200.dp, 800.dp).align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LogoTitle(Modifier.weight(7f))
            SearchBar(Modifier.weight(2f))
            Column(modifier = Modifier.weight(2f)) {
                Row(
                    modifier = Modifier.weight(2f).width(800.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        "Last Viewed",
                        fontSize = secondaryTitleFontSize,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.fillMaxSize()
                    )

                }

            }
            Box(modifier = Modifier.weight(6f, false), contentAlignment = Alignment.Center) {
                FileList(cardList)
            }
        }
    }
}

@Preview
@Composable
fun previewRecent() {
    MaterialTheme {
        RecentScreen()
    }
}