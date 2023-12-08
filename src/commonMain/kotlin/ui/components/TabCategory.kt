package ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.isCtrlPressed
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import kotlinx.io.files.Path
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf
import viewmodel.FileSaveViewModel

sealed class TabCategory(val name: String, val screen: @Composable (TabCategory) -> Unit = { }) {
    var isSaved by mutableStateOf(true)

    data class Edit(val path: Path) : TabCategory(path.name, {

        val viewModel: FileSaveViewModel = koinInject<FileSaveViewModel> { parametersOf(path) }
        LaunchedEffect(viewModel.isSaved) {
            (it as Edit).isSaved = viewModel.isSaved
        }
        MarkdownField(
            {
                viewModel.text = it
            }, viewModel.text, modifier = Modifier.fillMaxSize().onPreviewKeyEvent {
                when {
                    (it.isCtrlPressed && it.key == Key.S) -> {
                        viewModel.saveFile()
                        true
                    }

                    else -> false
                }
            }
        )
    })

    data class Tree(val path: Path) : TabCategory("Tree")
    data class View(val path: Path) : TabCategory("View")
    data object Empty : TabCategory("Empty")


}