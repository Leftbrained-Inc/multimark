package ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import com.bumble.appyx.components.backstack.BackStack
import com.bumble.appyx.components.backstack.operation.push
import com.darkrockstudios.libraries.mpfilepicker.FilePicker
import core.configuration.LocalConfiguration
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.io.files.Path
import navigation.NavTarget
import ui.components.FileList
import ui.components.LogoTitle
import ui.components.SearchBar
import ui.utils.dp

/**
 * Стартовый экран
 * @param backStack Стек навигации
 * @author Марат Белоцерковский (MIAPROT)
 * @author Сергей Рейнн (bulkabuka)
 * @author Василий Панков (pank-su)
 */
@Composable
fun LaunchScreen(backStack: BackStack<NavTarget>) {
    var showPicker by remember { mutableStateOf(false) }
    val launchScreen = LocalConfiguration.current.launchScreen
    val files by launchScreen.recentFiles.collectAsState(listOf())
    var search by remember { mutableStateOf("") }
    Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        Column(
            Modifier.widthIn(200.dp, 600.dp).align(if (files.isNotEmpty()) Alignment.TopCenter else Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(12.dp, alignment = Alignment.CenterVertically)
        ) {
            // TODO исправить выравнивание
            LogoTitle(
                (if (files.isEmpty()) Modifier.weight(1f).fillMaxWidth().padding(24.dp) else Modifier.height(64.dp)),
                files.isNotEmpty()
            )
            Box(modifier = if (files.isEmpty()) Modifier.weight(1f) else Modifier) {
                Row(
                    modifier = Modifier.height(100.dp).fillMaxWidth().shadow(4.dp, RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.tertiaryContainer, shape = RoundedCornerShape(16.dp))
                        .padding(24.dp),
                    verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    Button(onClick = { showPicker = !showPicker }, Modifier.width(120.dp).height(40.dp)) {
                        Text(text = "Open", style = MaterialTheme.typography.labelLarge)
                    }
                    if (showPicker) {
                        FilePicker(true, fileExtensions = listOf("md")) { file ->
                            if (file != null) {
                                val path = Path(file.path)
                                CoroutineScope(Dispatchers.IO).launch {
                                    launchScreen.addRecentFile(path)
                                }
                                backStack.push(NavTarget.MainScreen(path))
                            }
                            showPicker = false
                        }
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
            }
            // Список недавно просмотренных
            AnimatedVisibility(files.isNotEmpty(), modifier = Modifier) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        "Last Viewed",
                        style = MaterialTheme.typography.headlineMedium,
                    )
                    FileList(files, modifier = Modifier.padding(top = 24.dp))
                }
            }
        }
        Text(
            "Crafted with ❤\uFE0F in Leftbrained",
            modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 24.dp),
            fontWeight = FontWeight(500),
        )
    }
}
