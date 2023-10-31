package ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import models.FileDTO
import ui.utils.dp


/**
 * Компонент списка файлов
 * @param cardList Передаваемый список файлов
 * @param modifier Установка размеров
 *
 * @author Марат Белоцерковский (MIAPROT)
 * @author Сергей Рейнн (bulkabuka)
 */
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun FileList(cardList: List<FileDTO>, modifier: Modifier) {
    val windowSizeClass = calculateWindowSizeClass()
    var docSize by remember {
        mutableStateOf(30.dp)
    }
    LaunchedEffect(windowSizeClass) {
        docSize = when (windowSizeClass.widthSizeClass) {
            WindowWidthSizeClass.Compact -> 12.dp
            WindowWidthSizeClass.Medium -> 24.dp
            WindowWidthSizeClass.Expanded -> 30.dp
            else -> 10.dp
        }
    }
    LazyVerticalGrid(modifier = modifier, columns = GridCells.Adaptive(minSize = 128.dp)) {
        items(cardList.size) {
            val card = cardList[it]
            Card(
                Modifier.padding(end = 10.dp, bottom = 10.dp).fillMaxWidth().shadow(4.dp, RoundedCornerShape(12.dp))
                    .border(
                        BorderStroke(1.dp, MaterialTheme.colorScheme.outline), RoundedCornerShape(12.dp)
                    )
            ) {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Image(
                        modifier = Modifier.size(docSize),
                        painter = painterResource("doc.svg"),
                        contentDescription = null
                    )
                    Text(text = card.name, style = MaterialTheme.typography.titleMedium)
                    Text(
                        text = card.date.toString(),
                        modifier = Modifier.padding(top = 8.dp),
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }
    }
}