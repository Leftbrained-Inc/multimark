package ui.utils

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

/**
 * Cтатический класc для получения текущего масштаба интерфейса
 *
 * @author Панков Вася (pank-su)
 */
class Scale{
    companion object{
        var scale by mutableStateOf(1.0f)
    }
}