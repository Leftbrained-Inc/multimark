package viewmodel

import androidx.compose.runtime.*
import kotlinx.io.buffered
import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem
import kotlinx.io.readString
import kotlinx.io.writeString

class FileSaveViewModel(val path: Path){
    var saveNow by mutableStateOf(false)
    val contentSaved by derivedStateOf { val buffer = SystemFileSystem.source(path).buffered()
        val text = buffer.readString()
        buffer.close()
        text }
    var text by mutableStateOf(contentSaved)
    val isSaved by derivedStateOf { text == contentSaved }

    fun saveFile(){
        val sink = SystemFileSystem.sink(path).buffered()

        sink.writeString(text)
        sink.close()
    }

}