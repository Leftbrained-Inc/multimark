package ui.screen

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
import kotlinx.io.InternalIoApi
import kotlinx.io.files.Path
import navigation.NavTarget
import org.koin.compose.rememberKoinInject
import ui.components.FileList
import ui.components.LogoTitle
import ui.components.SearchBar
import ui.utils.dp
import viewmodel.FilesViewModel

/**
 * Стартовый экран
 *
 * @author Белоцерковский Марат (MIAPROT)
 * @author Сергей Рейнн (bulkabuka)
 * @author Панков Вася (pank-su)
 */
@OptIn(InternalIoApi::class)
@Composable
fun LaunchScreen(backStack: BackStack<NavTarget>) {
    val viewModel: FilesViewModel = rememberKoinInject()
    var showPicker by remember { mutableStateOf(false) }
    val files by viewModel.recentFiles.collectAsState(listOf())
    var search by remember { mutableStateOf("") }
    Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        Column(
            Modifier.widthIn(200.dp, 600.dp).align(Alignment.TopCenter),
            verticalArrangement = Arrangement.spacedBy(12.dp, alignment = Alignment.CenterVertically)
        ) {
            LogoTitle(Modifier.fillMaxWidth(), true)
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
                            viewModel.addRecentFile(path)
                            backStack.push(NavTarget.FileView(path))
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
                    backStack.push(NavTarget.SettingsScreen)
                }) {
                    Icon(
                        modifier = Modifier.weight(1f).size(36.dp),
                        imageVector = Icons.Default.Settings,
                        contentDescription = null
                    )
                }
            }
            // Список недавно просмотренных
            Column(modifier = Modifier.weight(6f, false), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    "Last Viewed",
                    style = MaterialTheme.typography.headlineMedium,
                )
                FileList(files, modifier = Modifier.padding(top = 24.dp))
            }
        }
        Text(
            "Crafted with ❤\uFE0F in Leftbrained",
            modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 24.dp),
            fontWeight = FontWeight(500),
        )
    }
}
