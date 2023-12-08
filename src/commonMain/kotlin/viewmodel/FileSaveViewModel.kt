package viewmodel

import androidx.compose.runtime.*
import kotlinx.io.buffered
import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem
import kotlinx.io.readString
import kotlinx.io.writeString

class FileSaveViewModel(val path: Path){

    private var contentText by mutableStateOf("")

    var text by mutableStateOf("")
    val isSaved by derivedStateOf { contentText == text }

    init {
        val buffer = SystemFileSystem.source(path).buffered()
        text = buffer.readString()
        contentText = text
        buffer.close()
    }

    fun saveFile(){
        val sink = SystemFileSystem.sink(path).buffered()

        sink.writeString(text)
        sink.close()
        contentText = text
    }

}