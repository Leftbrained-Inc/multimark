package ui.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import kotlinx.io.Source
import kotlinx.io.readString

@Composable
fun FileView(file: Source){
    val content = file.readString()
    Text(content)
}
