package core.dsl.configuration.window

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.loadSvgPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.useResource
import androidx.compose.ui.unit.Density
import core.configuration.window.Window
import core.dsl.ConfigurationTagMaker
import org.jetbrains.compose.resources.resource
import java.io.File

/**
 * Реализация WindowBuilderPlatform
 *
 * @property icon
 * @property title
 *
 * @author Панков Вася (pank-su)
 */
@ConfigurationTagMaker
actual abstract class WindowBuilderPlatform {
    var icon: Painter = useResource("logo.svg"){loadSvgPainter(it, Density(100f)) }
    var title: String = "Multimark"

    actual open fun build(): Window {
        return Window(icon, title)
    }
}