package ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import core.configuration.LocalConfiguration
import ui.utils.dp

/**
 * Элемент логотипа
 * @param modifier Установка размеров
 * @param showTitle Отображение названия
 * @author Марат Белоцерковский (MIAPROT)
 * @author Сергей Рейнн (bulkabuka)
 * @author Василий Панков (pank-su)
 */
@Composable
fun LogoTitle(modifier: Modifier, showTitle: Boolean) {
    val config = LocalConfiguration.current

    Row(
        modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        config.logo(Modifier.widthIn(max=400.dp).fillMaxHeight())
        if (showTitle) {
            Text(text = config.name, style = MaterialTheme.typography.headlineMedium, modifier = Modifier)
        }
    }
}
