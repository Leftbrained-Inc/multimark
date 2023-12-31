package core.extensions

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.multiplatform.webview.web.WebViewState
import dev.datlag.kcef.KCEF
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.intellij.markdown.flavours.gfm.GFMFlavourDescriptor
import org.intellij.markdown.html.HtmlGenerator
import org.intellij.markdown.parser.MarkdownParser
import java.io.File
import kotlin.math.max

/**
 * Конвертация Markdown в HTML
 * @param markdown Исходный текст
 * @return HTML-текст
 * @author Сергей Рейнн (bulkabuka)
 */
actual fun markdownToHtml(markdown: String): String {
    val flavour = GFMFlavourDescriptor()
    val parsedTree = MarkdownParser(flavour).buildMarkdownTreeFromString(markdown)
    return HtmlGenerator(markdown, parsedTree, flavour).generateHtml()
}

/**
 * Предпросмотр Markdown
 * @param state Состояние WebView
 * @see WebViewState
 * @author Сергей Рейнн (bulkabuka)
 * @author Василий Панков (pank-su)
 */
@Composable
actual fun WebView(state: WebViewState) {
    var restartRequired by remember { mutableStateOf(false) }
    var downloading by remember { mutableStateOf(0F) }
    var initialized by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            KCEF.init(builder = {
                installDir(File("kcef-bundle"))
                progress {
                    onDownloading {
                        downloading = max(it, 0F)
                    }
                    onInitialized {
                        initialized = true
                    }
                }
                settings {
                    cachePath = File("cache").absolutePath
                }
            }, onError = {
                it?.printStackTrace()

            }, onRestartRequired = {
                restartRequired = true
            })
        }
    }
    if (restartRequired) {
        Text(text = "Restart required.")
    } else {
        if (initialized) {
            com.multiplatform.webview.web.WebView(state, modifier = Modifier.fillMaxSize())
        } else {
            Text(text = "Downloading $downloading%")
        }
    }
}
