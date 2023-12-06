package ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Transition
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import core.configuration.LocalConfiguration
import ui.screen.launchscreen.LaunchScreenState
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
        AnimatedVisibility(showTitle){
            Text(text = config.name, style = MaterialTheme.typography.headlineMedium, modifier = Modifier)
        }
    }
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun LogoTitle(modifier: Modifier, transition: Transition<LaunchScreenState>) {
    val config = LocalConfiguration.current

    Row(
        modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        config.logo(Modifier.animateContentSize().fillMaxHeight())
        transition.AnimatedVisibility({it == LaunchScreenState.HasFiles}){
            Text(text = config.name, style = MaterialTheme.typography.headlineMedium, modifier = Modifier)
        }
    }
}