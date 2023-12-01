package core.extensions

import androidx.compose.runtime.Composable
import com.multiplatform.webview.web.WebViewState

@Composable
expect fun WebView(state: WebViewState)

/**
 * Конвертация Markdown в HTML
 * @param markdown Исходный текст
 * @return HTML-текст
 * @author Сергей Рейнн (bulkabuka)
 */
expect fun markdownToHtml(markdown: String): String