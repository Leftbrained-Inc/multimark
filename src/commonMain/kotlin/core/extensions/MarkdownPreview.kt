package core.extensions

import androidx.compose.runtime.Composable
import javax.swing.text.html.HTMLEditorKit

@Composable
expect fun MarkdownPreview(html: String, editorKit: HTMLEditorKit)