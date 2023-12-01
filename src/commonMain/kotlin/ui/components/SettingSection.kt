package ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import models.SettingSectionDTO

@Composable
fun SettingSection(
    settingSectionDTO: SettingSectionDTO,
    onSectionSelected: (Int) -> Unit,
    sections: List<SettingSectionDTO>,
) {
    Row(
        Modifier.padding(8.dp).clickable { onSectionSelected(sections.indexOf(settingSectionDTO)) }.background(
            color = (if (settingSectionDTO.isSelected.value) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.background),
            shape = RoundedCornerShape(16.dp)
        ).padding(
            8.dp
        ).fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            Modifier.padding(8.dp).background(
                color = (if (settingSectionDTO.isSelected.value) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primaryContainer),
                shape = RoundedCornerShape(16.dp)
            ).padding(8.dp).size(36.dp), contentAlignment = Alignment.Center
        ) {
            settingSectionDTO.icon(
                Modifier,
                if (settingSectionDTO.isSelected.value) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
        Text(
            settingSectionDTO.name,
            style = MaterialTheme.typography.labelLarge,
            color = if (settingSectionDTO.isSelected.value) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onBackground
        )
    }
}