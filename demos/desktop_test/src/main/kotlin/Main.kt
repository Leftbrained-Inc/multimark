import androidx.compose.foundation.Image
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import core.dsl.configuration.config

fun main() {
    config {
        logo {
            Image(
                painterResource("Good Tick.svg"), null, modifier = it,
                contentScale = ContentScale.FillHeight
            )
        }
        scale = 1f
        fontScale = 1f
    }
}
