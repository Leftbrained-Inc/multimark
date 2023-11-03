import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ui.screen.StartScreen


class StartScreenTest {


    @get:Rule
    val rule = createComposeRule()

    @Test
    fun testInitialUIState() {
        rule.setContent {
            StartScreen()
        }
            // Заготовка для тестов
    }
}
