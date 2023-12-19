package ui.screen.launchscreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import com.bumble.appyx.components.backstack.BackStack
import com.bumble.appyx.components.backstack.operation.push
import com.darkrockstudios.libraries.mpfilepicker.FilePicker
import core.configuration.LocalConfiguration
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.io.files.Path
import navigation.NavTarget
import org.koin.compose.koinInject
import ui.components.FileList
import ui.components.LogoTitle
import ui.components.SearchBar
import ui.components.TabCategory
import ui.utils.dp
import viewmodel.TabViewmodel

/**
 * Стартовый экран
 * @param backStack Стек навигации
 * @author Марат Белоцерковский (MIAPROT)
 * @author Сергей Рейнн (bulkabuka)
 * @author Василий Панков (pank-su)
 */
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun LaunchScreen(backStack: BackStack<NavTarget>) {
    var showPicker by remember { mutableStateOf(false) }
    val launchScreen = LocalConfiguration.current.launchScreen
    val files by launchScreen.recentFiles.collectAsState(listOf())

    val state by remember(files) {
        derivedStateOf {
            if (files.isEmpty()) LaunchScreenState.NoFiles else LaunchScreenState.HasFiles
        }
    }

    val transition = updateTransition(state, label = "transition")

    var search by remember { mutableStateOf("") }
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background).padding(24.dp)
    ) {
        val height by remember(this.maxHeight) {
            derivedStateOf { this.maxHeight }
        }
        Column(
            Modifier.align(Alignment.TopCenter),
            verticalArrangement = Arrangement.spacedBy(12.dp, alignment = Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val logoTitleHeight by transition.animateDp(
                transitionSpec = { tween(durationMillis = 1000) },
                label = "Height for logotitle"
            ) { state ->
                when {
                    state == LaunchScreenState.NoFiles && launchScreen.isFirstLoad -> height / 2
                    state == LaunchScreenState.HasFiles -> 64.dp
                    else -> 64.dp
                }
            }
            LogoTitle(
                Modifier.fillMaxWidth().height(logoTitleHeight),
                transition,
                logoTitleHeight
            )
            Row(
                modifier = Modifier.height(100.dp).widthIn(200.dp, 600.dp).fillMaxWidth()
                    .shadow(4.dp, RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.tertiaryContainer, shape = RoundedCornerShape(16.dp))
                    .padding(24.dp),
                verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                Button(onClick = { showPicker = !showPicker }, Modifier.width(120.dp).height(40.dp)) {
                    Text(text = "Open", style = MaterialTheme.typography.labelLarge)
                }

                val tabViewModel: TabViewmodel = koinInject()
                FilePicker(showPicker, fileExtensions = listOf("md", "*")) { file ->
                    if (file != null) {
                        val path = Path(file.path)
                        CoroutineScope(Dispatchers.IO).launch {
                            launchScreen.addRecentFile(path)
                        }
                        tabViewModel.tabs.add(TabCategory.Edit(path))
                        backStack.push(NavTarget.MainScreen(path))
                    }
                    showPicker = false
                }

                SearchBar(
                    value = search,
                    onValueChange = { newText -> search = newText },
                    modifier = Modifier.weight(9f).fillMaxSize().height(50.dp),
                )
                IconButton(onClick = {

                }) {
                    Icon(
                        modifier = Modifier.weight(1f).size(36.dp),
                        imageVector = Icons.Default.Settings,
                        contentDescription = null
                    )
                }
            }

            // Список недавно просмотренных
            transition.AnimatedVisibility(
                { it == LaunchScreenState.HasFiles },
                enter = fadeIn(tween(durationMillis = 1000, delayMillis = 1000))
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        "Last Viewed",
                        style = MaterialTheme.typography.headlineMedium,
                    )
                    FileList(files, modifier = Modifier.padding(top = 24.dp),backStack)
                }
            }

        }
        Text(
            "Crafted with ❤\uFE0F in Leftbrained",
            modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 24.dp).clip(
                CircleShape
            ).background(MaterialTheme.colorScheme.surfaceVariant).padding(6.dp),
            fontWeight = FontWeight(500),
        )
        LaunchedEffect(Unit) {
            delay(1000)
            launchScreen.isFirstLoad = false || state == LaunchScreenState.NoFiles
        }
    }
}
