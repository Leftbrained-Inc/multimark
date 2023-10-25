package navigation

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import com.bumble.appyx.navigation.modality.BuildContext
import com.bumble.appyx.navigation.node.Node
import core.dsl.elements.configuration.ConfigurationImpl
import core.dsl.elements.configuration.LocalConfiguration
import ui.screen.StartScreen


/**
 * Основной компонент навигации
 *
 * @author Панков Вася (pank-su)
 */
class RootNode(
    buildContext: BuildContext
) : Node(
    buildContext = buildContext
){

    /**
     * Отображение экрана
     *
     * @author Панков Вася (pank-su)
     */
    @Composable
    override fun View(modifier: Modifier) {
        StartScreen()
    }
}