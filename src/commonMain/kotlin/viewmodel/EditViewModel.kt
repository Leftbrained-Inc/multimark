package viewmodel

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.io.buffered
import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem
import kotlinx.io.readString
import kotlinx.io.writeString
import kotlin.time.Duration.Companion.minutes

/**
 * Класс ViewModel для редактирования файла
 * @property textFieldValue Значение текстового поля
 * @property contentText Сохраненный текст
 * @property isSaved Флаг сохранения
 * @author Алексей Челноков (shizik-tech)
 * @author Василий Панков (pank-su)
 */
class EditViewModel(val path: Path) {

    private var contentText by mutableStateOf("")

    val isSaved by derivedStateOf {
        contentText == textFieldValue.text
    }
    var textFieldValue by mutableStateOf(TextFieldValue(""))

    /**
     * Чтение файла
     */
    init {
        val buffer = SystemFileSystem.source(path).buffered()
        textFieldValue = TextFieldValue(buffer.readString())
        contentText = textFieldValue.text
        buffer.close()
        reserveFile()
    }

    /**
     * Функция сохранения файла
     */
    fun saveFile() {
        val sink = SystemFileSystem.sink(path).buffered()
        sink.writeString(textFieldValue.text)
        sink.close()
        contentText = textFieldValue.text
    }

    /**
     * Функция резервного сохранения файла
     */
    fun reserveFile() {
        CoroutineScope(Dispatchers.IO).launch {
            val reserve = Path(path.parent ?: Path(""), "." + path.name)
            while (true) {
                delay(1.minutes)
                val sink = SystemFileSystem.sink(reserve).buffered()
                sink.writeString(textFieldValue.text)
                sink.close()
            }
        }
    }

}