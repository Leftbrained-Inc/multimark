package navigation

import com.bumble.appyx.utils.multiplatform.Parcelable
import com.bumble.appyx.utils.multiplatform.Parcelize


/**
 * Будущий показатель всех возможных назначений в навигации
 * @author Панков Вася (pank-su)
 */
sealed class NavTarget : Parcelable {
    @Parcelize
    object Child1 : NavTarget()

}