package ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import core.db.RecentFile
import ui.utils.dp

/**
 * Компонент списка файлов
 *
 * @param fileList Передаваемый список файлов
 * @param modifier Установка размеров
 * @author Марат Белоцерковский (MIAPROT)
 * @author Сергей Рейнн (bulkabuka)
 * @author Панков Вася (pank-su)
 */
@Composable
fun FileList(fileList: List<RecentFile>, modifier: Modifier) {
    LazyVerticalGrid(modifier = modifier, columns = GridCells.Adaptive(minSize = 200.dp)) {
        items(fileList.size) {
            val file = fileList[it]
            Card(
                Modifier.padding(end = 10.dp, bottom = 10.dp).fillMaxWidth().shadow(4.dp, RoundedCornerShape(12.dp))
                    .border(
                        BorderStroke(1.dp, MaterialTheme.colorScheme.outline), RoundedCornerShape(12.dp)
                    )
            ) {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Image(
                        Icons.Outlined.Description,
                        contentDescription = null, modifier = Modifier.size(30.dp),
                    )
                    Text(text = file.fileName, style = MaterialTheme.typography.titleMedium)
                    Text(
                        text = file.dateOfApplication.toString(),
                        modifier = Modifier.padding(top = 8.dp),
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }
    }
}
