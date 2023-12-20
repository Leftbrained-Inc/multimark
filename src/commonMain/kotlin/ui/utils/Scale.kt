package ui.utils

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ui.utils.Scale.Companion.fontScale
import ui.utils.Scale.Companion.scale

/**
 * Cтатический класc для получения текущего масштаба интерфейса
 *
 * @property scale масштабирование экрана
 * @property fontScale масштабирование шрифта
 *
 * @author Василий Панков (pank-su)
 */
class Scale {
    companion object {
        var scale by mutableStateOf(1f)
        var fontScale by mutableStateOf(1f)
    }
}