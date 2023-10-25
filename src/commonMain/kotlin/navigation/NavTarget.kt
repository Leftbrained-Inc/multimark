package navigation

import com.bumble.appyx.utils.multiplatform.Parcelable
import com.bumble.appyx.utils.multiplatform.Parcelize

sealed class NavTarget : Parcelable {
    @Parcelize
    object Child1 : NavTarget()

}