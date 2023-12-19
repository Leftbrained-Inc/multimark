package ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.HorizontalScrollbar
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import core.configuration.LocalConfiguration
import kotlinx.coroutines.launch
import org.koin.compose.koinInject
import ui.theme.MultimarkAppTheme
import ui.utils.dp
import viewmodel.TabViewmodel
import viewmodel.onDragEnd

/**
 * Строка с вкладками для переключения между экранами редактора
 * @author Алексей Челноков (shizik-tech)
 * @author Сергей Рейнн (bulkabuka)
 * @author Василий Панков (pank-su)
 * @param modifier Modifier
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabRow(modifier: Modifier) {
    val tabViewmodel: TabViewmodel = koinInject()
    val selectedTabIndex by tabViewmodel.selectedTabIndex.collectAsState()
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
            items(tabViewmodel.tabs.size) { tabId ->
                val tab by remember { derivedStateOf { tabViewmodel.tabs.getOrNull(tabId) } }
                val coroutineScope = rememberCoroutineScope()
                val configuration = LocalConfiguration.current
                val density = LocalDensity.current
                Box(modifier = Modifier.fillMaxWidth().widthIn(max = 200.dp).pointerInput(Unit) {

                    detectDragGestures(
                        onDragStart = {
                            tab?.dragTabState?.isDrag = true
                        },
                        onDragEnd = {
                            tab?.dragTabState?.isDrag = false
                            tabViewmodel.onDragEnd(tab!!, configuration, density)
                            tab?.dragTabState?.offset = Offset.Zero
                        },
                        onDragCancel = {
                            tab?.dragTabState?.isDrag = false
                            tab?.dragTabState?.offset = Offset.Zero
                        }) { change, dragAmount ->
                        change.consume()
                        if (tab != null)
                            tab!!.dragTabState.offset += dragAmount
                    }
                }) {
                    AnimatedVisibility(tab?.dragTabState?.isDrag == false, modifier = Modifier.animateItemPlacement()) {
                        if (tab != null)
                            Tab(
                                tab!!,
                                tabId == selectedTabIndex,
                                onClick = {
                                    coroutineScope.launch {
                                        tabViewmodel.select(tabId)
                                    }
                                },
                                Modifier.onGloballyPositioned {
                                    if (tab?.dragTabState?.isDrag == true) return@onGloballyPositioned
                                    tab!!.dragTabState.position = Pair(it.positionInWindow().x, it.positionInWindow().y)
                                    tab!!.size = it.size
                                }
                            )
                    }
                }
            }
        }
        HorizontalScrollbar(
            modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth(), adapter = rememberScrollbarAdapter(
                scrollState = state
            )
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Tab(tab: TabCategory, selected: Boolean, onClick: () -> Unit, modifier: Modifier) {
    InputChip(
        selected = selected,
        onClick =
        onClick,
        label = { Text(tab.name, textAlign = TextAlign.Center) },
        colors = InputChipDefaults.inputChipColors(
            containerColor = MaterialTheme.colorScheme.onPrimary,
            labelColor = MaterialTheme.colorScheme.onSecondaryContainer,
            selectedContainerColor = MaterialTheme.colorScheme.primary,
            selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
            trailingIconColor = MaterialTheme.colorScheme.onSecondaryContainer,
            selectedTrailingIconColor = MaterialTheme.colorScheme.onPrimary
        ),
        border = InputChipDefaults.inputChipBorder(
            borderWidth = 0.dp, borderColor = Color.Transparent, selectedBorderColor = Color.Transparent
        ),
        trailingIcon = {
            AnimatedVisibility(tab is TabCategory.Edit && !tab.isSaved) {
                Icon(
                    Icons.Default.Circle,
                    null
                )
            }
        },
        modifier = modifier
    )
}

@Preview
@Composable
fun TabRowPreview() {
    MultimarkAppTheme {
        Surface {
            TabRow(Modifier.fillMaxWidth())
        }
    }
}