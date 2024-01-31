package ui.screen

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
 * @author Гамуйло Сергей (bulkabuka)
 * @author Белоцерковский Марат (MIAPROT)
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
                Modifier.weight(1f).background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(16.dp))
                    .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(16.dp)).fillMaxHeight()
                    .padding(12.dp).widthIn(min = 100.dp, max = 150.dp)
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
            Column(Modifier.weight(3f).border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(16.dp))
                    .fillMaxHeight().background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(16.dp))
                    .padding(2.dp)
            ) {
                sections[numSelected].content()
            }
        }
    }
}

// TODO Сделать ограничения для SettingsSection и самого экрана настроек. Сделать сами экраны настроек. Пустить в продакшн

@Preview
@Composable
fun preview(){
    Surface {
        SettingScreen(listOf(SettingSectionDTO( "General", {modifier, color ->
            Icon(Icons.Default.Settings, null, tint = color, modifier = modifier)
        }, { Text(text = "General")}), SettingSectionDTO( "DSL Config", {modifier, color ->
            Icon(Icons.Default.Settings, null, tint = color, modifier = modifier)
        }, { Text(text = "DSL Config")}) ), 1, {})
    }
}
