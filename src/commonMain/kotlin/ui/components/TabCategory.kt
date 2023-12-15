package ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.isCtrlPressed
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.unit.IntSize
import kotlinx.io.files.Path
import viewmodel.EditViewModel
import kotlin.random.Random

/**
 * Категории вкладок
 * @author Сергей Рейнн (bulkabuka)
 * @author Василий Панков (pank-su)
 * @param name Название вкладки
 * @param screen Открытый экран
 * */
sealed class TabCategory(val name: String, var screen: @Composable (TabCategory) -> Unit = { }) {
    /**
     * Вкладка редактирования файла
     * @param path Путь к файлу
     * @param viewModel ViewModel класса редактора
     * */
    data class Edit(val path: Path, private val viewModel: EditViewModel = EditViewModel(path)) :
        TabCategory(path.name) {
        val isSaved by derivedStateOf { viewModel.isSaved }

        init {
            screen = {
                MarkdownField(
                    {
                        viewModel.textFieldValue = it
                    }, viewModel.textFieldValue, modifier = Modifier.fillMaxSize().onPreviewKeyEvent {
                        when {
                            (it.isCtrlPressed && it.key == Key.S) -> {
                                viewModel.saveFile()
                                true
                            }

                            else -> false
                        }
                    }
                )
            }
        }
    }

    /**
     * TODO
     * Дерево файлов
     * */
    data class Tree(val path: Path) : TabCategory("Tree")
    data class View(val path: Path) : TabCategory("View")

    /**
     * Пустая вкладка для тестирования
     */
    class Empty : TabCategory("Empty ${Random.nextInt()}")


    val dragTabState: DragTabState = DragTabState()

    var size: IntSize = IntSize.Zero
}