package ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import models.FileDTO


/**
 * Список файлов
 * @param cardList передоваемый список файлов
 * @author Марат Белоцерковский (MIAPROT)
 */
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun FileList(cardList: List<FileDTO>): Unit {
    val windowSizeClass = calculateWindowSizeClass()
    var docSize by remember {
        mutableStateOf(30.dp)
    }
    LaunchedEffect(windowSizeClass) {
        docSize = when (windowSizeClass.widthSizeClass) {
            WindowWidthSizeClass.Compact -> 20.dp
            WindowWidthSizeClass.Medium -> 25.dp
            WindowWidthSizeClass.Expanded -> 30.dp
            else -> 10.dp
        }
    }
    LazyVerticalGrid(modifier = Modifier, columns = GridCells.Adaptive(minSize = 128.dp)) {
        items(cardList.size) {
            val card = cardList[it]
            Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                Column {
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