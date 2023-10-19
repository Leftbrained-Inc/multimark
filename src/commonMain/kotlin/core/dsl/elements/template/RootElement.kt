package core.dsl.elements.template

import androidx.compose.runtime.Composable

interface RootElement {
    @Composable
    fun render(content: @Composable () -> Unit)
}