package ui.theme

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp

/**
 * Шаблоны тем для превью
 * @property fonts Список шрифтов, доступных к выбору
 * @property lineHeights Список интерлиньяжа
 * @property fontSizes Список размеров шрифтов
 * @author Сергей Рейнн (bulkabuka)
 */
class PreviewTheme {
    private val fonts = mutableListOf(FontFamily.Serif, FontFamily.SansSerif, FontFamily.Monospace, FontFamily.Cursive)
    private val lineHeights = mutableListOf(1.0f, 1.5f, 2.0f, 2.5f, 3.0f)
    private val fontSizes = mutableListOf(12.sp, 14.sp, 16.sp, 18.sp, 20.sp, 22.sp, 24.sp, 26.sp, 28.sp, 30.sp)
}