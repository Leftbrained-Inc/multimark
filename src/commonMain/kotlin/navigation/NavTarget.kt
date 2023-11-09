package navigation

import com.bumble.appyx.utils.multiplatform.Parcelable
import com.bumble.appyx.utils.multiplatform.Parcelize
import kotlinx.io.Source


/**
 * Будущий показатель всех возможных назначений в навигации
 * @author Панков Вася (pank-su)
 */
sealed class NavTarget : Parcelable {
    @Parcelize
    class FileView(val file: Source) : NavTarget()

    @Parcelize
    object LaunchScreen : NavTarget()

    @Parcelize
    object SettingsScreen : NavTarget()

}