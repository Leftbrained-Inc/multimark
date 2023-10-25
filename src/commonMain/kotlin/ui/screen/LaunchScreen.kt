package ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import core.dsl.elements.configuration.LocalConfiguration

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun LaunchScreen() {
    val config = LocalConfiguration.current
    val search = remember { mutableStateOf("") }
    val windowSizeClass = calculateWindowSizeClass()
    var titleFontSize by remember {
        mutableStateOf(30.sp)
    }
    var basicFont by remember {
        mutableStateOf(30.sp)
    }
    var logoSize by remember {
        mutableStateOf(150.dp)
    }

    LaunchedEffect(windowSizeClass) {
        titleFontSize = when (windowSizeClass.widthSizeClass) {
            WindowWidthSizeClass.Compact -> 30.sp
            WindowWidthSizeClass.Medium -> 40.sp
            WindowWidthSizeClass.Expanded -> 60.sp
            else -> 10.sp
        }
        basicFont = when (windowSizeClass.widthSizeClass) {
            WindowWidthSizeClass.Compact -> 16.sp
            WindowWidthSizeClass.Medium -> 18.sp
            WindowWidthSizeClass.Expanded -> 20.sp
            else -> 10.sp
        }
        logoSize = when (windowSizeClass.widthSizeClass) {
            WindowWidthSizeClass.Compact -> 48.dp
            WindowWidthSizeClass.Medium -> 64.dp
            WindowWidthSizeClass.Expanded -> 96.dp
            else -> 10.dp
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {


        Column(
            Modifier.padding(24.dp).widthIn(min = 200.dp, max = 500.dp).align(Alignment.TopCenter),
            verticalArrangement = Arrangement.spacedBy(12.dp, alignment = Alignment.CenterVertically)
        ) {
            Column(
                Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(48.dp, alignment = Alignment.CenterVertically)
            ) {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp, alignment = Alignment.CenterHorizontally),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    config.icon(Modifier.size(logoSize))
                    Text(text = "Multimark", style = MaterialTheme.typography.headlineMedium)
                }
                Row(
                    Modifier.fillMaxWidth()
                        .background(MaterialTheme.colorScheme.tertiaryContainer, shape = RoundedCornerShape(16.dp))
                        .padding(12.dp), verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = search.value,
                        maxLines = 1,
                        onValueChange = { newText -> search.value = newText },
                        textStyle = MaterialTheme.typography.bodyMedium,
                        trailingIcon = {
                            Icon(
                                painter = painterResource("search.svg"),
                                contentDescription = "Search Loupe",
                                modifier = Modifier.size(48.dp)
                            )
                        },
                        shape = RoundedCornerShape(20.dp),
                        placeholder = {
                            Text("Search", style = MaterialTheme.typography.bodyMedium)
                        },
                        modifier = Modifier.padding(start = 10.dp, top = 4.dp, bottom = 4.dp, end = 40.dp).weight(8f),
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = MaterialTheme.colorScheme.onTertiary,
                            focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                            focusedContainerColor = MaterialTheme.colorScheme.onTertiary
                        )
                    )
                    Image(
                        modifier = Modifier.weight(2f).size(width = 30.dp, height = 30.dp),
                        painter = painterResource("settings.svg"),
                        contentDescription = null
                    )
                }
            }
            Button(onClick = {}, Modifier.fillMaxWidth()) {
                Text(text = "New note", style = MaterialTheme.typography.labelLarge)
            }
            OutlinedButton(onClick = {}, Modifier.fillMaxWidth()) {
                Text(text = "Last viewed", style = MaterialTheme.typography.labelLarge)
            }
            OutlinedButton(onClick = {}, Modifier.fillMaxWidth()) {
                Text(text = "Pinned", style = MaterialTheme.typography.labelLarge)
            }
        }
    }
}