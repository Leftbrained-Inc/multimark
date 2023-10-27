package ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import core.dsl.elements.configuration.LocalConfiguration
import models.FileDTO
import ui.components.FileList
import ui.components.LogoTitle
import ui.components.SearchBar
import java.util.*

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

    Box(modifier = Modifier.fillMaxSize()) {


        Column(
            Modifier.fillMaxSize().align(Alignment.TopCenter)
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.spacedBy(12.dp, alignment = Alignment.CenterVertically)
        ) {
            Column(
                Modifier.padding(24.dp).fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(48.dp, alignment = Alignment.CenterVertically)
            ) {
                LogoTitle(Modifier.fillMaxWidth())
                SearchBar(Modifier.height(100.dp))
                // Список недавно просмотренных
                Box(modifier = Modifier.weight(6f, false), contentAlignment = Alignment.Center) {
                    Text(
                        "Last Viewed",
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.fillMaxSize().padding(bottom = 24.dp)
                    )
                    FileList(cardList, modifier = Modifier.padding(top = 24.dp))
                }
            }
        }
    }
}