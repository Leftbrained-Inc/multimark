package ui.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.HorizontalScrollbar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import ui.theme.MultimarkAppTheme
import ui.utils.dp

enum class Test {
    One
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun TabRow(tabs: List<String>, modifier: Modifier) {
    var selectedIndex by remember {
        mutableIntStateOf(0)
    }
    val state = rememberLazyListState()
    Box(
        modifier.height(80.dp).background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(16.dp))
    ) {
        LazyRow(
            modifier.height(80.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            state = state
        ) {
            item {
                Spacer(modifier = Modifier.width(24.dp))
            }
            items(tabs.size) {
                InputChip(
                    selected = it == selectedIndex,
                    onClick = { selectedIndex = it },
                    label = { Text(tabs[it], textAlign = TextAlign.Center) },
                    colors = InputChipDefaults.inputChipColors(
                        containerColor = MaterialTheme.colorScheme.onPrimary,
                        labelColor = MaterialTheme.colorScheme.onSecondaryContainer,
                        selectedContainerColor = MaterialTheme.colorScheme.primary,
                        selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    border = InputChipDefaults.inputChipBorder(
                        borderWidth = 0.dp, borderColor = Color.Transparent, selectedBorderColor = Color.Transparent
                    ),
                    modifier = Modifier.fillMaxWidth().widthIn(max = 200.dp).animateItemPlacement()
                )
            }
        }
        HorizontalScrollbar(
            modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth(), adapter = rememberScrollbarAdapter(
                scrollState = state
            )
        )
    }
}

@Preview
@Composable
fun TabRowPreview() {
    MultimarkAppTheme {
        Surface {
            TabRow(List(3) { "Tab $it" }, Modifier)
        }
    }
}