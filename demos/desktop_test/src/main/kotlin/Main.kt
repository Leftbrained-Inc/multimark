import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.application
import core.dsl.config

//@Composable
//@Preview
//fun App() {
//    var text by remember { mutableStateOf("Hello, World!") }
//
//    MaterialTheme {
//        Button(onClick = {
//            text = "Hello, Desktop!"
//        }) {
//            Text(text)
//        }
//    }
//}

fun main() {
    application {
        config {
            icon {
                Image(painterResource("Good Tick.svg"), null, modifier = it)
            }
            this.name = "test123"
        }.render(::exitApplication)
    }

}
