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
import models.CardDTO
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

    // Изменение шрифта при изменении экрана
    LaunchedEffect(windowSizeClass) {
        titleFontSize = when (windowSizeClass.widthSizeClass) {
            WindowWidthSizeClass.Compact -> 30.sp
            WindowWidthSizeClass.Medium -> 40.sp
            WindowWidthSizeClass.Expanded -> 60.sp
            else -> 10.sp
        }
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
        logoSize = when (windowSizeClass.widthSizeClass) {
            WindowWidthSizeClass.Compact -> 100.dp
            WindowWidthSizeClass.Medium -> 125.dp
            WindowWidthSizeClass.Expanded -> 150.dp
            else -> 10.dp
        }
        docSize = when (windowSizeClass.widthSizeClass) {
            WindowWidthSizeClass.Compact -> 20.dp
            WindowWidthSizeClass.Medium -> 25.dp
            WindowWidthSizeClass.Expanded -> 30.dp
            else -> 10.dp
        }
    }

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Row(Modifier.weight(7f), verticalAlignment = Alignment.CenterVertically) {
            Image(
                modifier = Modifier.size(logoSize),
                painter = painterResource("logo.svg"),
                contentDescription = null
            )
            androidx.compose.material3.Text(text = "Multimark", fontSize = titleFontSize)
        }
        Row(
            modifier = Modifier.weight(2f).width(800.dp)
                .background(Color(0xFFfed7e2), shape = RoundedCornerShape(40.dp)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(value = search.value,
                onValueChange = { newText -> search.value = newText },
                textStyle = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Normal),
                shape = RoundedCornerShape(25.dp),
                modifier = Modifier.padding(start = 10.dp, top = 4.dp, bottom = 4.dp, end = 40.dp).weight(9f)
                    .background(Color.White, shape = RoundedCornerShape(25.dp))
                    .border(3.dp, Color.White, shape = RoundedCornerShape(25.dp)),
                placeholder = {
                    androidx.compose.material3.Text(
                        text = "Search",
                        color = Color(0xFF2F0F1C),
                        fontSize = 20.sp
                    )
                },
                trailingIcon = {
                    Icon(
                        painter = painterResource("search.svg"),
                        contentDescription = null,
                        modifier = Modifier.size(32.dp)
                    )
                }
            )

            Image(
                modifier = Modifier.weight(1f).size(width = 30.dp, height = 30.dp),
                painter = painterResource("settings.svg"),
                contentDescription = null
            )
        }
        Column(modifier = Modifier.weight(2f)) {
            Row(
                modifier = Modifier.weight(2f).width(800.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                androidx.compose.material3.Text(
                    "Last Viewed",
                    fontSize = secondaryTitleFontSize,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(top = 40.dp)
                )

            }

        }
        LazyVerticalGrid(modifier = Modifier.weight(6f), horizontalArrangement = Arrangement.Center, columns = GridCells.Adaptive(minSize = 128.dp)) {
            items(cardList.size) {
                val card = cardList[it]
                Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
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

@Preview
@Composable
fun previewRecent() {
    MaterialTheme {
        RecentScreen()
    }
}