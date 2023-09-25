package UI.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun WindowStart() {
    MaterialTheme {
        val search = remember { mutableStateOf("") }
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            Row(Modifier.weight(7f), verticalAlignment = Alignment.CenterVertically) {
                Image(
                    modifier = Modifier.size(width = 150.dp, height = 150.dp),
                    painter = painterResource("MultiMarkLogo.png"),
                    contentDescription = null
                )
                Text(text = "Multimark", fontSize = 60.sp)
            }
            Row(
                modifier = Modifier.weight(2f).width(800.dp)
                    .background(Color(0xFFfed7e2), shape = RoundedCornerShape(40.dp)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                androidx.compose.material.OutlinedTextField(value = search.value,
                    onValueChange = { newText -> search.value = newText },
                    textStyle = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Normal),
                    shape = RoundedCornerShape(25.dp),
                    modifier = Modifier.padding(12.dp).weight(9f)
                        .background(Color.White, shape = RoundedCornerShape(25.dp))
                        .border(3.dp, Color.White, shape = RoundedCornerShape(25.dp)),
                    placeholder = {
                        Text(
                            text = "Search",
                            color = Color(0xFF2F0F1C),
                            fontSize = 20.sp
                        )
                    },
                    trailingIcon = {
                        Icon(
                            painter = painterResource("search.png"),
                            contentDescription = null,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                )

                Image(
                    modifier = Modifier.weight(1f).size(width = 30.dp, height = 30.dp),
                    painter = painterResource("settings.png"),
                    contentDescription = null
                )
            }
            Row(modifier = Modifier.weight(11f)) {}
        }
    }
}