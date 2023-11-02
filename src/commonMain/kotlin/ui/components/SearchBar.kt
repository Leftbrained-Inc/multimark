package ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import ui.utils.dp

/**
 * Элемент поисковой строки
 * @param modifier установка размеров
 *
 * @author Белоцерковский Марат (MIAPROT)
 * @author Сергей Рейнн (bulkabuka)
 * @author Панков Вася (pank-su)
 */
@Composable
fun SearchBar(modifier: Modifier) {
    val search = remember { mutableStateOf("") }
    Row(
        modifier = modifier.fillMaxWidth().heightIn(min = 70.dp, max = 80.dp).shadow(4.dp, RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.tertiaryContainer, shape = RoundedCornerShape(16.dp)).padding(24.dp),
        verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Button(onClick = {}, Modifier.width(120.dp).height(40.dp)) {
            Text(text = "New note", style = MaterialTheme.typography.labelLarge)
        }
        OutlinedTextField(value = search.value,
            onValueChange = { newText -> search.value = newText },
            textStyle = MaterialTheme.typography.bodyMedium,
            shape = CircleShape,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.onTertiary,
                focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                focusedContainerColor = MaterialTheme.colorScheme.onTertiary
            ),
            singleLine = true,
            modifier = Modifier.weight(9f).fillMaxSize().height(50.dp),
            placeholder = {
                Text(
                    text = "Search",
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            trailingIcon = {
                Icon(
                    painter = painterResource("search.svg"),
                    contentDescription = "Search loupe",
                    modifier = Modifier.size(48.dp)
                )
            }
        )
        IconButton(onClick = {}) {
            Icon(
                modifier = Modifier.weight(1f).size(36.dp),
                painter = painterResource("settings.svg"),
                contentDescription = null
            )
        }
    }
}