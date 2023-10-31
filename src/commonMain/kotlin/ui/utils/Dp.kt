package ui.utils

import androidx.compose.ui.unit.Dp

/**
 * dp with scale
 *
 * @author Панков Вася (pank-su)
 */

inline val Int.dp: Dp
    get() = Dp(value = this.toFloat() * Scale.scale)

/**
 * dp withoutScale
 *
 * @author Панков Вася (pank-su)
 */
inline val Int.dps: Dp
    get() = Dp(value = this.toFloat())