package models

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

data class SettingSectionDTO(
    var isSelected: MutableState<Boolean> = mutableStateOf(false),
    val name: String,
    val icon: @Composable (Modifier, Color) -> Unit,
    val content: @Composable () -> Unit
)