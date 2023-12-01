package ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle

/**
 * Поле ввода Markdown-текста
 * @param onValueChange Действие при изменении значения
 * @param text Начальный текст
 * @param textStyle Стиль текста
 * @param modifier Modifier
 * @author Сергей Рейнн (bulkabuka)
 */
@Composable
fun MarkdownField(
    onValueChange: (String) -> Unit,
    text: String,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    modifier: Modifier
) {
    TextField(
        value = text,
        onValueChange = onValueChange,
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
        modifier = modifier,
        placeholder = {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    )
}