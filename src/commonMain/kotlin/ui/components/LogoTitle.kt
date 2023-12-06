package ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Transition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import core.configuration.LocalConfiguration
import ui.screen.launchscreen.LaunchScreenState

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
        config.logo(Modifier.fillMaxHeight())
        AnimatedVisibility(showTitle) {
            Text(text = config.name, style = MaterialTheme.typography.headlineMedium, modifier = Modifier)
        }
    }
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun LogoTitle(modifier: Modifier, transition: Transition<LaunchScreenState>, imageHeight: Dp) {
    val config = LocalConfiguration.current
    Row(
        modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        config.logo(Modifier.height(imageHeight))
        transition.AnimatedVisibility({ it == LaunchScreenState.HasFiles }) {
            Text(text = config.name, style = MaterialTheme.typography.headlineMedium, modifier = Modifier)
        }
    }
}