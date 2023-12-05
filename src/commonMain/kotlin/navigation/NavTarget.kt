package navigation

import com.bumble.appyx.utils.multiplatform.Parcelable
import com.bumble.appyx.utils.multiplatform.Parcelize
import kotlinx.io.files.Path


/**
 * Будущий показатель всех возможных назначений в навигации
 * @author Василий Панков (pank-su)
 */
sealed class NavTarget : Parcelable {
    @Parcelize
    class FileView(val file: Path) : NavTarget()

    @Parcelize
    object LaunchScreen : NavTarget()

    @Parcelize
    object SettingsScreen : NavTarget()

    @Parcelize
    data class MainScreen(val file: Path) : NavTarget()
}