package ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.isCtrlPressed
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import kotlinx.io.files.Path
import viewmodel.EditViewModel

sealed class TabCategory(val name: String, var screen: @Composable (TabCategory) -> Unit = { }) {

    data class Edit(val path: Path, private val viewModel: EditViewModel = EditViewModel(path)) :
        TabCategory(path.name){
            val isSaved by derivedStateOf { viewModel.isSaved }
            init {
                screen = {
                    MarkdownField(
                        {
                            viewModel.textFieldValue = it
                        }, viewModel.textFieldValue, modifier = Modifier.fillMaxSize().onPreviewKeyEvent {
                            when {
                                (it.isCtrlPressed && it.key == Key.S) -> {
                                    viewModel.saveFile()
                                    true
                                }
                                else -> false
                            }
                        }
                    )
                }
            }
        }

    data class Tree(val path: Path) : TabCategory("Tree")
    data class View(val path: Path) : TabCategory("View")
    data object Empty : TabCategory("Empty")


}