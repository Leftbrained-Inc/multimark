package models

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

/**
 * Модель для секции настроек
 * @param isSelected Состояние выбора секции
 * @param name Название секции
 * @param icon Иконка секции
 * @param content Содержимое секции
 * @author Сергей Рейнн (bulkabuka)
 */
data class SettingSectionDTO(
    val name: String,
    val icon: @Composable (Modifier, Color) -> Unit,
    val content: @Composable () -> Unit,
    var isSelected: MutableState<Boolean> = mutableStateOf(false)
)