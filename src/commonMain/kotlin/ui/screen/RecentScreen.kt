package ui.screen

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import core.dsl.elements.configuration.LocalConfiguration
import models.CardDTO
import ui.components.LogoTitle
import ui.components.SearchBar
import java.security.AllPermission
import java.util.*


@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun RecentScreen() {
    val search = remember { mutableStateOf("") }
    val windowSizeClass = calculateWindowSizeClass()
    var titleFontSize by remember {
        mutableStateOf(30.sp)
    }
    var secondaryTitleFontSize by remember {
        mutableStateOf(30.sp)
    }
    var basicFont by remember {
        mutableStateOf(30.sp)
    }
    var logoSize by remember {
        mutableStateOf(150.dp)
    }
    var docSize by remember {
        mutableStateOf(30.dp)
    }
    val cardList = remember {
        mutableStateListOf<CardDTO>(CardDTO("Test1", Date()), CardDTO("Test 2", Date()))
    }
    val config = LocalConfiguration.current

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
        docSize = when (windowSizeClass.widthSizeClass) {
            WindowWidthSizeClass.Compact -> 20.dp
            WindowWidthSizeClass.Medium -> 25.dp
            WindowWidthSizeClass.Expanded -> 30.dp
            else -> 10.dp
        }
    }

    Box(Modifier.fillMaxSize()) {

        Column(modifier = Modifier.widthIn(200.dp, 800.dp).align(Alignment.TopCenter), horizontalAlignment = Alignment.CenterHorizontally) {
            LogoTitle(Modifier.weight(7f))
            SearchBar(Modifier.weight(2f))
            Column(modifier = Modifier.weight(2f)) {
                Row(
                    modifier = Modifier.weight(2f).width(800.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    androidx.compose.material3.Text(
                        "Last Viewed",
                        fontSize = secondaryTitleFontSize,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.fillMaxSize()
                    )

                }

            }
            Box(modifier = Modifier.weight(6f, false), contentAlignment = Alignment.Center) {
                LazyVerticalGrid(modifier = Modifier, columns = GridCells.Adaptive(minSize = 128.dp)) {
                    items(cardList.size) {
                        val card = cardList[it]
                        Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                            Column() {
                                Image(
                                    modifier = Modifier.size(docSize),
                                    painter = painterResource("doc.svg"),
                                    contentDescription = null
                                )
                                Text(text = card.name, fontWeight = FontWeight.Bold)
                                Text(text = card.date.toString(), modifier = Modifier.padding(top = 8.dp))
                            }
                        }
                    }
                }
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