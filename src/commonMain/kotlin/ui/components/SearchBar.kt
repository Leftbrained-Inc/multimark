package ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SearchBar(modifier: Modifier){
    val search = remember { mutableStateOf("") }
    Row(
        modifier = modifier.width(800.dp)
            .background(Color(0xFFfed7e2), shape = RoundedCornerShape(40.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(value = search.value,
            onValueChange = { newText -> search.value = newText },
            textStyle = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Normal),
            shape = RoundedCornerShape(25.dp),
            modifier = Modifier.padding(start = 10.dp, top = 4.dp, bottom = 4.dp, end = 40.dp).weight(9f)
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
                    painter = painterResource("search.svg"),
                    contentDescription = null,
                    modifier = Modifier.size(32.dp)
                )
            }
        )

        Image(
            modifier = Modifier.weight(1f).size(width = 30.dp, height = 30.dp),
            painter = painterResource("settings.svg"),
            contentDescription = null
        )
    }
}