package navigation

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.bumble.appyx.components.backstack.BackStack
import com.bumble.appyx.components.backstack.BackStackModel
import com.bumble.appyx.components.backstack.ui.fader.BackStackFader
import com.bumble.appyx.navigation.composable.AppyxComponent
import com.bumble.appyx.navigation.modality.BuildContext
import com.bumble.appyx.navigation.node.Node
import com.bumble.appyx.navigation.node.ParentNode
import com.bumble.appyx.navigation.node.node
import models.SettingSectionDTO
import ui.screen.FileView
import ui.screen.MainScreen
import ui.screen.SettingScreen

/**
 * Основной компонент навигации
 * @author Панков Вася (pank-su)
 */
class RootNode(
    buildContext: BuildContext,
    private val backStack: BackStack<NavTarget> = BackStack(
        model = BackStackModel(
            initialTarget = NavTarget.LaunchScreen,
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
     * @author Панков Вася (pank-su)
     */
    @Composable
    override fun View(modifier: Modifier) {
        Surface(modifier, color = MaterialTheme.colorScheme.background) {
            AppyxComponent(backStack)
        }
    }

    /**
     * Сопоставление путей к экранам
     * @author Панков Вася (pank-su)
     */
    override fun resolve(interactionTarget: NavTarget, buildContext: BuildContext): Node =
        when (interactionTarget) {
            NavTarget.LaunchScreen -> node(buildContext) {
                var selected by remember { mutableStateOf(1) }
                SettingScreen(
                    listOf(
                        SettingSectionDTO(mutableStateOf(false), "General", { _, _ -> }, { Text("One") }),
                        SettingSectionDTO(mutableStateOf(true), "Second", { _, _ -> }, { Text("Two") }),
                        SettingSectionDTO(mutableStateOf(false), "Third", { _, _ -> }, { Text("Three") }),
                    ), selected
                ) {
                    selected = it
                }
            }

            is NavTarget.FileView -> node(buildContext) {
                FileView(interactionTarget.file)
            }

            NavTarget.SettingsScreen -> node(buildContext) {
                Text("Настройки")
            }

            NavTarget.MainScreen -> node(buildContext) {
                MainScreen()
            }
        }
}