package core.extensions

import androidx.compose.runtime.Composable

@Composable
expect fun MarkdownPreview(html: String)

/**
 * Конвертация Markdown в HTML
 * @param markdown Исходный текст
 * @return HTML-текст
 * @author Сергей Рейнн (bulkabuka)
 */
expect fun markdownToHtml(markdown: String): String