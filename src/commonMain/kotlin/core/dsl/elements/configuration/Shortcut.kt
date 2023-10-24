package core.dsl.elements.configuration

import androidx.compose.runtime.Composable
import androidx.compose.ui.input.key.*

data class Shortcut(val condition: (KeyEvent) -> Boolean, val action: () -> Unit)


val italicShortcut = Shortcut({ it.key == Key.I && it.isMetaPressed && it.type == KeyEventType.KeyDown }) {
    // Применить курсивное форматирование
    println("Italic used")
}

val boldShortcut = Shortcut({ it.key == Key.B && it.isMetaPressed && it.type == KeyEventType.KeyDown }) {
    // Применить жирное форматирование
    println("Bold used")
}

val underlineShortcut = Shortcut({ it.key == Key.U && it.isMetaPressed && it.type == KeyEventType.KeyDown }) {
    // Применить подчеркивание
    println("Underline used")
}

val strikethroughShortcut = Shortcut({ it.key == Key.S && it.isMetaPressed && it.type == KeyEventType.KeyDown }) {
    // Зачеркнуть текст
    println("Strike used")
}

val highlightShortcut = Shortcut({ it.key == Key.M && it.isMetaPressed && it.type == KeyEventType.KeyDown }) {
    // Выделить текст
    println("Highlight used")
}

val linkShortcut = Shortcut({ it.key == Key.L && it.isMetaPressed && it.type == KeyEventType.KeyDown }) {
    // Создать ссылку
    println("Link used")
}

val mediaShortcut = Shortcut({ it.key == Key.M && it.isMetaPressed && it.isShiftPressed && it.type == KeyEventType.KeyDown }) {
    // Добавить картинку или видео
    println("Media used")
}

val formulaShortcut = Shortcut({ it.key == Key.E && it.isMetaPressed && it.type == KeyEventType.KeyDown }) {
    // Создать формулу
    println("Formula used")
}

val codeShortcut = Shortcut({ it.key == Key.C && it.isMetaPressed && it.type == KeyEventType.KeyDown }) {
    // Применить форматирование кода
    println("Code used")
}

val quoteShortcut = Shortcut({ it.key == Key.Q && it.isMetaPressed && it.type == KeyEventType.KeyDown  }) {
    // Создать блок цитаты
    println("Quote used")
}

val headingShortcut = Shortcut({ it.isMetaPressed && it.key in listOf(Key.Button1, Key.Button2, Key.Button3, Key.Button4, Key.Button5, Key.Button6) && it.key == Key.N && it.type == KeyEventType.KeyDown }) {
    // Создать заголовок выбранного уровня
    println("Heading used")
}

val codeBlockShortcut = Shortcut({ it.key == Key.C && it.isMetaPressed && it.isShiftPressed && it.type == KeyEventType.KeyDown }) {
    // Создать многострочный блок кода с подсветкой кода
    println("Codeblock used")
}

val numberedlistShortcut = Shortcut({ it.key == Key.N && it.isMetaPressed && it.type == KeyEventType.KeyDown }) {
    // Создать нумерованный список
    println("Numbered list used")
}

val bulletedListShortcut = Shortcut({ it.key == Key.D && it.isMetaPressed && it.type == KeyEventType.KeyDown }) {
    // Создать ненумерованный список
    println("Bulleted list used")
}

val tableShortcut = Shortcut({ it.key == Key.T && it.isMetaPressed && it.type == KeyEventType.KeyDown }) {
    // Создать таблицу требуемого размера
    println("Table used")
}

val shorts = listOf(tableShortcut, bulletedListShortcut, numberedlistShortcut,
    codeBlockShortcut, headingShortcut, quoteShortcut, codeShortcut, formulaShortcut, mediaShortcut, linkShortcut,
    highlightShortcut, strikethroughShortcut, underlineShortcut, boldShortcut, italicShortcut)
