package ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import models.SettingSectionDTO
import ui.components.SettingSection

/**
 * Экран настроек приложения
 * @param sections Список секций настроек
 * @param selected Выбранная секция
 * @param onSectionSelected Обработчик выбора секции
 * @see SettingSectionDTO
 * @author Сергей Рейнн (bulkabuka)
 */
@Composable
fun SettingScreen(
    sections: List<SettingSectionDTO>,
    selected: Int,
    onSectionSelected: (Int) -> Unit
) {
    var numSelected by mutableStateOf(selected)
    Surface(color = MaterialTheme.colorScheme.background) {
        Row(Modifier.padding(16.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Column(
                Modifier.weight(1f).border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(16.dp))
                    .fillMaxHeight().background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(16.dp))
                    .padding(2.dp)
            ) {
                sections.forEach { section ->
                    SettingSection(
                        section,
                        {
                            numSelected = sections.indexOf(section)
                            onSectionSelected(sections.indexOf(section))
                            section.isSelected = mutableStateOf(true)
                        },
                        sections
                    )
                }
            }
        }
        Column(
            Modifier.background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(16.dp))
                .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(16.dp)).fillMaxHeight()
                .padding(16.dp)
        ) {
            sections[numSelected].content()
        }
    }
}
// TODO Доработать настройки и пустить в продакшн