package ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import core.configuration.LocalConfiguration
import ui.utils.dp

/**
 * Элемент логотипа
 * @param modifier Установка размеров
 *
 * @author Белоцерковский Марат (MIAPROT)
 * @author Сергей Рейнн (bulkabuka)
 * @author Панков Вася (pank-su)
 */
@Composable
fun LogoTitle(modifier: Modifier, showTitle: Boolean) {
    val config = LocalConfiguration.current

    Row(
        modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp, alignment = Alignment.CenterHorizontally)
    ) {
        config.logo(Modifier.size(64.dp))
        if (showTitle) {
            Text(text = "Multimark", style = MaterialTheme.typography.headlineMedium)
        }
    }
}
