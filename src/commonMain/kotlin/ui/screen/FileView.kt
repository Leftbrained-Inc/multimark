package ui.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import kotlinx.io.buffered
import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem
import kotlinx.io.readString

/**
 * Экран, показывающий содержимое файла
 * @param path Путь к файлу
 * @author Сергей Рейнн (bulkabuka)
 */
@Composable
fun FileView(path: Path) {
    val content = SystemFileSystem.source(path).buffered().readString()
    Text(content)
}