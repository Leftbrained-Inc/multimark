package navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.navigation.modality.BuildContext
import com.bumble.appyx.navigation.node.Node
import ui.components.Tree
import ui.screen.LaunchScreen

/**
 * Основной компонент навигации
 * @author Панков Вася (pank-su)
 */
class RootNode(
    buildContext: BuildContext
) : Node(
    buildContext = buildContext
){

    /**
     * Отображение экрана
     * @author Панков Вася (pank-su)
     */
    @Composable
    override fun View(modifier: Modifier) {
        LaunchScreen()
    }
}