package ui.utils

import androidx.compose.ui.unit.Dp

/**
 * dp, который изменяется в зависимости от установленного масшатаба интерефейса в [Scale.scale]
 *
 * @author Василий Панков (pank-su)
 */

inline val Int.dp: Dp
    get() = Dp(value = this.toFloat() * Scale.scale)

/**
 * Стандартный dp, которому без разницы на установленный масштаб интерфейса в [Scale.scale]
 *
 * @author Василий Панков (pank-su)
 */
inline val Int.dps: Dp
    get() = Dp(value = this.toFloat())