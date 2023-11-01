package ui.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ui.theme.MultimarkAppTheme
import ui.theme.md_theme_light_primaryContainer

/**
 * Элемент navbar
 * @author Белоцерковский Марат(MIAPROT)
 * @param directory путь к файлу
 */
@Composable
//TODO Заменить параметр по умолчанию
fun NavBar(directory: String = "D:\\Battle.net\\Battle.net.13521\\audio") {
    val pathList = directory.split("\\")
    val file = pathList.last()
    val search = remember { mutableStateOf("") }

    BoxWithConstraints(Modifier.fillMaxWidth()) {
        val width = this.maxWidth
        LaunchedEffect(width) {
            println(width)
        }
        Row(
            modifier = Modifier.height(80.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.height(80.dp),
                painter = painterResource("logo.svg"),
                contentDescription = null,
                contentScale = ContentScale.FillHeight
            )
            Row(
                Modifier.background(md_theme_light_primaryContainer, shape = RoundedCornerShape(25.dp)).fillMaxHeight()
                    .weight(10f), verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(painter = painterResource("folder.svg"), null)
                Text(
                    text = pathList.subList(0, pathList.lastIndex).joinToString("  |  "),
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Normal)
                )
                Text(text = "  |  ", style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Normal))
                Icon(painter = painterResource("book.svg"), null)
                Text(text = file, style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Normal))

            }
            if (width > 900.dp) {
                Row(
                    modifier = Modifier.weight(6f).fillMaxHeight()
                        .background(Color(0xFFfed7e2), shape = RoundedCornerShape(25.dp)),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(value = search.value,
                        onValueChange = { newText -> search.value = newText },
                        shape = RoundedCornerShape(25.dp),
                        modifier = Modifier.padding(start = 10.dp, top = 4.dp, bottom = 4.dp, end = 40.dp).weight(9f)
                            .background(Color.White, shape = RoundedCornerShape(25.dp))
                            .border(3.dp, Color.White, shape = RoundedCornerShape(25.dp)),
                        placeholder = {
                            Text(
                                text = "Search",
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
                    Spacer(Modifier.width(12.dp))
                    Image(
                        modifier = Modifier.weight(1f).size(width = 30.dp, height = 30.dp),
                        painter = painterResource("palette.svg"),
                        contentDescription = null
                    )
                }
            }
        }
    }

}

@Preview
@Composable
fun previewNavBar() {
    MultimarkAppTheme {
        Surface(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
            NavBar()
        }
    }
}