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
 * ViewModel функции сохронения изменений
 * @property text переменная в которую заносится  текст
 * @property contentText переменная в которую заносится изменённый текст
 * @property isSaved переменная проверки на совподение сохронённого текста
 * @author Алексей Челноков (shizik-tech)
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
     * Функция сохранения
     * */
    fun saveFile() {
        val sink = SystemFileSystem.sink(path).buffered()
        sink.writeString(textFieldValue.text)
        sink.close()
        contentText = textFieldValue.text
    }

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