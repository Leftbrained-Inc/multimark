package core.dsl.elements.template

import androidx.compose.runtime.Composable


/** Обычный элемент конфигурации
 * @author Панков Вася (pank-su)
 */
interface Element {
    @Composable
    fun render()
}