package ui.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle

/**
 * Поле ввода Markdown-текста
 * @param textStyle Стиль текста
 * @author Сергей Рейнн (bulkabuka)
 */
@Composable
fun MarkdownField(textStyle: TextStyle) {
    val field = remember { mutableStateOf("Hello, **world**!") }
    TextField(value = field.value,
        onValueChange = { newText -> field.value = newText },
        textStyle = textStyle,
        shape = RectangleShape,
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.background,
            focusedBorderColor = MaterialTheme.colorScheme.background,
            focusedContainerColor = MaterialTheme.colorScheme.background,
            cursorColor = MaterialTheme.colorScheme.primary,
            focusedTextColor = MaterialTheme.colorScheme.onBackground,
            unfocusedTextColor = MaterialTheme.colorScheme.onBackground
        ),
        modifier = androidx.compose.ui.Modifier.fillMaxSize(),
        placeholder = {
            Text(
                text = "Hello, **World**!",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    )
}

@Preview
@Composable
fun MarkdownFieldPreview() {
    MarkdownField(MaterialTheme.typography.bodyMedium)
}