package ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ui.utils.dp

/**
 * Элемент поисковой строки
 * @param modifier Установка размеров
 * @param value Значение поисковой строки
 * @param onValueChange Действие при изменинии значения
 * @author Белоцерковский Марат (MIAPROT)
 * @author Сергей Рейнн (bulkabuka)
 * @author Панков Вася (pank-su)
 */
@Composable
fun SearchBar(value: String, onValueChange: (String) -> Unit, modifier: Modifier) {
    OutlinedTextField(value = value,
        onValueChange = onValueChange,
        textStyle = MaterialTheme.typography.bodyMedium,
        shape = CircleShape,
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.onTertiary,
            focusedBorderColor = MaterialTheme.colorScheme.tertiary,
            focusedContainerColor = MaterialTheme.colorScheme.onTertiary
        ),
        singleLine = true,
        modifier = modifier.height(50.dp),
        placeholder = {
            Text(
                text = "Search",
                style = MaterialTheme.typography.bodyMedium
            )
        },
        trailingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = "Search loupe",
            )
        }
    )
}