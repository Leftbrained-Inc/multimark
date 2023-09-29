import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

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

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
