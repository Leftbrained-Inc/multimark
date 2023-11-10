package core.extensions

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.SwingPanel
import org.intellij.markdown.flavours.gfm.GFMFlavourDescriptor
import org.intellij.markdown.html.HtmlGenerator
import org.intellij.markdown.parser.MarkdownParser
import java.awt.Dimension
import javax.swing.JEditorPane
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.text.Document
import javax.swing.text.html.HTMLEditorKit

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
 * @param html HTML-текст
 * @see markdownToHtml
 * @author Сергей Рейнн (bulkabuka)
 */
@Composable
actual fun MarkdownPreview(html: String) {
    val editorKit = HTMLEditorKit()
    val jEditorPane = JEditorPane()
    val doc: Document = editorKit.createDefaultDocument()
    jEditorPane.setEditorKit(editorKit)
    jEditorPane.setDocument(doc)
    jEditorPane.text = html.trimIndent()
    jEditorPane.isEditable = false
    val scrollPane = JScrollPane(jEditorPane)
    scrollPane.size = Dimension(300, 400)
    scrollPane.isVisible = true

    SwingPanel(
        factory = {
            val panel = JPanel()
            panel.add(scrollPane)
        }
    )
}

@Preview
@Composable
fun MarkdownPreviewPreview() {
    val editorKit = HTMLEditorKit()
    editorKit.styleSheet.addRule(
        "" +
                "body { font-family: 'Roboto', sans-serif; " +
                "font-size: 14pt; " +
                "line-height: 1.5; " +

                "}"
    )
    MaterialTheme {
        Column(Modifier.fillMaxSize()) {
            MarkdownPreview(
                markdownToHtml(
                    """
                |# Hello World
                |
                |Some **text** with a [link](https://vk.com).
                |
                |> New paragraph 
            """.trimMargin()
                )
            )
        }

    }
}