package navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import com.bumble.appyx.utils.multiplatform.Parcelable
import com.bumble.appyx.utils.multiplatform.Parcelize
import kotlinx.io.files.Path
import models.SettingSectionDTO


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
    class SettingsScreen(val settingsList:List<SettingSectionDTO> = listOf(SettingSectionDTO( "General", {modifier, color ->
        Icon(Icons.Default.Settings, null, tint = color, modifier = modifier)
    }, { Text(text = "General") }), SettingSectionDTO( "DSL Config", {modifier, color ->
        Icon(Icons.Default.Settings, null, tint = color, modifier = modifier)
    }, { Text(text = "DSL Config") }) ), selectedItem: Int =  1, onSectionSelected: (Int) -> Unit = {}) : NavTarget()

    @Parcelize
    object MainScreen : NavTarget()
}