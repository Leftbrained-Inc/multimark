import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.application
import core.dsl.configuration.config

fun main() {
    application {
        val painter = painterResource("Good Tick.svg")
        config {
            icon {
                Image(painterResource("Good Tick.svg"), null, modifier = it)
            }

            window {
                icon = painter
                title = "Test"
            }
        }.render(::exitApplication)

    }
}
