package navigation

import com.bumble.appyx.utils.multiplatform.Parcelable
import com.bumble.appyx.utils.multiplatform.Parcelize
import kotlinx.io.files.Path


/**
 * Будущий показатель всех возможных назначений в навигации
 * @author Панков Вася (pank-su)
 */
sealed class NavTarget : Parcelable {
    @Parcelize
    class FileView(val file: Path) : NavTarget()

    @Parcelize
    object LaunchScreen : NavTarget()

    @Parcelize
    object SettingsScreen : NavTarget()

    @Parcelize
    object MainScreen : NavTarget()
}