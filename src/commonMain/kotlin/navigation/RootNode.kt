package navigation

import androidx.compose.animation.AnimatedContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.bumble.appyx.components.backstack.BackStack
import com.bumble.appyx.components.backstack.BackStackModel
import com.bumble.appyx.components.backstack.ui.fader.BackStackFader
import com.bumble.appyx.navigation.composable.AppyxComponent
import com.bumble.appyx.navigation.modality.BuildContext
import com.bumble.appyx.navigation.node.Node
import com.bumble.appyx.navigation.node.ParentNode
import com.bumble.appyx.navigation.node.node
import core.configuration.LocalConfiguration
import ui.screen.FileView
import ui.screen.MainScreen
import ui.screen.SettingScreen
import ui.screen.launchscreen.LaunchScreen
import ui.utils.Scale

/**
 * Основной компонент навигации
 * @author Василий Панков (pank-su)
 */
class RootNode(
    buildContext: BuildContext,
    private val initialTarget: NavTarget = NavTarget.LaunchScreen,
    private val backStack: BackStack<NavTarget> = BackStack(
        model = BackStackModel(
            initialTarget = initialTarget,
            savedStateMap = buildContext.savedStateMap,
        ),
        visualisation = { BackStackFader(it) }
    )
) : ParentNode<NavTarget>(
    appyxComponent = backStack,
    buildContext = buildContext
) {

    /**
     * Отображение экрана
     * @author Василий Панков (pank-su)
     */
    @Composable
    override fun View(modifier: Modifier) {
        Surface(modifier, color = MaterialTheme.colorScheme.background) {
            val config = LocalConfiguration.current
            LaunchedEffect(config.scale) {
                Scale.scale = config.scale
                Scale.fontScale = config.fontScale
            }
            AnimatedContent(Scale.scale) {
                AppyxComponent(backStack)
            }
        }
    }

    /**
     * Сопоставление путей к экранам
     * @author Василий Панков (pank-su)
     */
    override fun resolve(interactionTarget: NavTarget, buildContext: BuildContext): Node =
        when (interactionTarget) {
            NavTarget.LaunchScreen -> node(buildContext) {
                LaunchScreen(backStack)
            }

            is NavTarget.FileView -> node(buildContext) {
                FileView(interactionTarget.file)
            }

            is NavTarget.SettingsScreen -> node(buildContext) {
                SettingScreen(interactionTarget.settingsList, 1, {})
            }

            NavTarget.MainScreen -> node(buildContext) {
                MainScreen(backStack)
            }
        }
}