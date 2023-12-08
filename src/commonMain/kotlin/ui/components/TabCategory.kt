package ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.isCtrlPressed
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import kotlinx.io.buffered
import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem
import kotlinx.io.readString
import kotlinx.io.writeString

sealed class TabCategory(val name: String, val screen: @Composable () -> Unit = { }) {

    data class Edit(val path: Path) : TabCategory(path.name, {
        var text by remember { mutableStateOf(SystemFileSystem.source(path).buffered().readString()) }
        MarkdownField(
            {
                text = it
            }, text, modifier = Modifier.fillMaxSize().onPreviewKeyEvent {
                when {
                    (it.isCtrlPressed && it.key == Key.S) -> {
                        val sink = SystemFileSystem.sink(path).buffered()

                        sink.writeString(text)
                        sink.close()
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