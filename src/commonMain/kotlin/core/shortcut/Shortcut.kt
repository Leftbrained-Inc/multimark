package core.dsl.elements.shortcut

import androidx.compose.ui.input.key.*
import ui.utils.Scale

/** Обработка горячих клавиш
 * @param condition Условие, при котором будет вызвана [action]
 * @param action Действие, которое будет выполнено при выполнении [condition]
 * @author Сергей Рейнн (bulkabuka)
 */
data class Shortcut(val condition: (KeyEvent) -> Boolean, val action: () -> Unit)


val italicShortcut = Shortcut({ it.key == Key.I && it.isAltPressed && it.type == KeyEventType.KeyDown }) {
    // Применить курсивное форматирование
    println("Italic used")
}

val boldShortcut = Shortcut({ it.key == Key.B && it.isAltPressed && it.type == KeyEventType.KeyDown }) {
    // Применить жирное форматирование
    println("Bold used")
}

val underlineShortcut = Shortcut({ it.key == Key.U && it.isAltPressed && it.type == KeyEventType.KeyDown }) {
    // Применить подчеркивание
    println("Underline used")
}

val strikethroughShortcut = Shortcut({ it.key == Key.S && it.isAltPressed && it.type == KeyEventType.KeyDown }) {
    // Зачеркнуть текст
    println("Strike used")
}

val highlightShortcut = Shortcut({ it.key == Key.M && it.isAltPressed && it.type == KeyEventType.KeyDown }) {
    // Выделить текст
    println("Highlight used")
}

val linkShortcut = Shortcut({ it.key == Key.L && it.isAltPressed && it.type == KeyEventType.KeyDown }) {
    // Создать ссылку
    println("Link used")
}

val mediaShortcut =
    Shortcut({ it.key == Key.M && it.isAltPressed && it.isShiftPressed && it.type == KeyEventType.KeyDown }) {
        // Добавить картинку или видео
        println("Media used")
    }

val formulaShortcut = Shortcut({ it.key == Key.E && it.isAltPressed && it.type == KeyEventType.KeyDown }) {
    // Создать формулу
    println("Formula used")
}

val codeShortcut = Shortcut({ it.key == Key.C && it.isAltPressed && it.type == KeyEventType.KeyDown }) {
    // Применить форматирование кода
    println("Code used")
}

val quoteShortcut = Shortcut({ it.key == Key.Q && it.isAltPressed && it.type == KeyEventType.KeyDown }) {
    // Создать блок цитаты
    println("Quote used")
}

val headingShortcut = Shortcut({ it.isAltPressed && it.key == Key.H && it.type == KeyEventType.KeyDown }) {
    // Создать заголовок выбранного уровня
    println("Heading used")
}

val codeBlockShortcut =
    Shortcut({ it.key == Key.C && it.isAltPressed && it.isShiftPressed && it.type == KeyEventType.KeyDown }) {
        // Создать многострочный блок кода с подсветкой кода
        println("Codeblock used")
    }

val numberedlistShortcut = Shortcut({ it.key == Key.N && it.isAltPressed && it.type == KeyEventType.KeyDown }) {
    // Создать нумерованный список
    println("Numbered list used")
}

val bulletedListShortcut = Shortcut({ it.key == Key.D && it.isAltPressed && it.type == KeyEventType.KeyDown }) {
    // Создать ненумерованный список
    println("Bulleted list used")
}

val tableShortcut = Shortcut({ it.key == Key.T && it.isAltPressed && it.type == KeyEventType.KeyDown }) {
    // Создать таблицу требуемого размера
    println("Table used")
}

val zoomShortcutSub = Shortcut({ it.isCtrlPressed && it.key == Key.Minus }) {
    Scale.scale -= 0.1f
}

val zoomShortcutAdd = Shortcut({ it.isCtrlPressed && it.key == Key.Equals }) {
    Scale.scale += 0.1f
}


// Список из всех горячих клавиш
val shorts = listOf(
    tableShortcut, bulletedListShortcut, numberedlistShortcut,
    codeBlockShortcut, headingShortcut, quoteShortcut, codeShortcut, formulaShortcut, mediaShortcut, linkShortcut,
    highlightShortcut, strikethroughShortcut, underlineShortcut, boldShortcut, italicShortcut,
    zoomShortcutAdd, zoomShortcutSub
)