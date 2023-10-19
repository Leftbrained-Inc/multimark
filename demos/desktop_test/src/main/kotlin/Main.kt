import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.application
import core.dsl.elements.configuration.config

fun main() {
    application {
        config {
            icon {
                Image(painterResource("Good Tick.svg"), null, modifier = it)
            }
        }.render(::exitApplication)
    }

}
