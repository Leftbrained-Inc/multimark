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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import core.dsl.elements.configuration.LocalConfiguration


///
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun StartScreen() {
    val search = remember { mutableStateOf("") }
    val windowSizeClass = calculateWindowSizeClass()
    var titleFontSize by remember {
        mutableStateOf(30.sp)
    }
    var basicFont by remember {
        mutableStateOf(30.sp)
    }
    var logoSize by remember {
        mutableStateOf(150.dp)
    }

    // Изменение шрифта при изменении экрана
    LaunchedEffect(windowSizeClass) {
        titleFontSize = when (windowSizeClass.widthSizeClass) {
            WindowWidthSizeClass.Compact -> 30.sp
            WindowWidthSizeClass.Medium -> 40.sp
            WindowWidthSizeClass.Expanded -> 60.sp
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
    }

    val config = LocalConfiguration.current

    LaunchedEffect(null) {
        println(config.icon)
    }


    Column(modifier = Modifier.fillMaxSize().padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Row(Modifier.weight(7f), verticalAlignment = Alignment.CenterVertically) {
            config.icon(Modifier.size(logoSize))
            Spacer(Modifier.width(24.dp))
            Text(text = "Multimark", fontSize = titleFontSize)
        }
        Row(
            modifier = Modifier.weight(2f).width(800.dp)
                .background(MaterialTheme.colorScheme.tertiaryContainer, shape = RoundedCornerShape(16.dp)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(value = search.value,
                onValueChange = { newText -> search.value = newText },
                textStyle = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Normal),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.padding(start = 10.dp, top = 4.dp, bottom = 4.dp, end = 40.dp).weight(9f)
                    .background(Color.White, shape = RoundedCornerShape(20.dp))
                    .border(3.dp, Color.White, shape = RoundedCornerShape(20.dp)),
                placeholder = {
                    Text(
                        text = "Search",
                        color = Color(0xFF2F0F1C),
                        fontSize = 20.sp
                    )
                },
                trailingIcon = {
                    Icon(
                        painter = painterResource("search.svg"),
                        contentDescription = null,
                        modifier = Modifier.size(48.dp)
                    )
                }
            )

            Image(
                modifier = Modifier.weight(1f).size(width = 30.dp, height = 30.dp),
                painter = painterResource("settings.svg"),
                contentDescription = null
            )
        }
        Column(modifier = Modifier.weight(8f)) {
            Button(
                onClick = {},
                modifier = Modifier
                    .width(800.dp)
                    .height(100.dp)
                    .padding(top = 40.dp) // Здесь можно указать нужное значение отступа сверху
                , shape = (RoundedCornerShape(40.dp)) // Вы можете также задать скругление углов
            ) {
                Text(text = "New note", fontSize = basicFont)
            }
            OutlinedButton(
                onClick = {},
                modifier = Modifier
                    .width(800.dp)
                    .height(80.dp)
                    .padding(top = 20.dp) // Здесь можно указать нужное значение отступа сверху
                , shape = (RoundedCornerShape(40.dp))// Вы можете также задать скругление углов
            ) {
                Text(text = "Last viewed", fontSize = basicFont, fontWeight = FontWeight.Bold, color = Color.Blue)
            }
            OutlinedButton(
                onClick = {},
                modifier = Modifier
                    .width(800.dp)
                    .height(80.dp)
                    .padding(top = 20.dp) // Здесь можно указать нужное значение отступа сверху
                , shape = (RoundedCornerShape(40.dp))// Вы можете также задать скругление углов
            ) {
                Text(text = "Pinned", fontSize = basicFont, fontWeight = FontWeight.Bold, color = Color.Blue)
            }
        }
        Row(modifier = Modifier.weight(3f)) {
            Text(text = "Crafted with ❤️ in Leftbrained", fontWeight = FontWeight.Bold, color = Color.Black)
        }
    }


}