package viewmodel

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.io.buffered
import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem
import kotlinx.io.readString
import kotlinx.io.writeString

/**
 * ViewModel функции сохронения изменений
 * @property text переменная в которую заносится  текст
 * @property contentText переменная в которую заносится изменённый текст
 * @property isSaved переменная проверки на совподение сохронённого текста
 * @author Алексей Челноков (shizik-tech)
 */

class FileSaveViewModel(val path: Path) {

    private var contentText by mutableStateOf("")

    var text by mutableStateOf("")
    val isSaved by derivedStateOf {
        contentText == text
    }

    /**
     * Чтение файла
     */
    init {
        val buffer = SystemFileSystem.source(path).buffered()
        text = buffer.readString()
        contentText = text
        buffer.close()
    }

    /**
     * Функция сохранения
     * */
    fun saveFile() {
        val sink = SystemFileSystem.sink(path).buffered()

        sink.writeString(text)
        sink.close()
        contentText = text
    }

}