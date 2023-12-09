package ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.HorizontalScrollbar
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import kotlinx.coroutines.launch
import org.koin.compose.koinInject
import ui.theme.MultimarkAppTheme
import ui.utils.dp
import viewmodel.TabViewmodel

/**
 * Путь файла (FilePath)
 * @author Алексей Челноков (shizik-tech)
 * @author Сергей Рейнн (bulkabuka)
 * @author Василий Панков (pank-su)
 * @param tabViewmodel viewModel класса
 * @param selectedTabIndex выбранный индекс вкладки
 * @param state статус
 * @param tab вкладка
 */
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
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
            items(tabViewmodel.tabs.size) {
                val tab = tabViewmodel.tabs[it]
                val coroutineScope = rememberCoroutineScope()
                InputChip(
                    selected = it == selectedTabIndex,
                    onClick = {
                        coroutineScope.launch {
                            tabViewmodel.select(it)
                        }
                    },
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
            TabRow(Modifier.fillMaxWidth())
        }
    }
}