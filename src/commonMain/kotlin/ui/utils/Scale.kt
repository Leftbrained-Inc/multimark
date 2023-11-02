package ui.utils

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

/**
 * Cтатический класc для получения текущего масштаба интерфейса
 *
 * @property scale масштабирование экрана
 * @property fontScale масштабирование шрифта
 *
 * @author Панков Вася (pank-su)
 */
class Scale{
    companion object{
        var scale by mutableStateOf(1f)
        var fontScale by mutableStateOf(1f)
    }
}