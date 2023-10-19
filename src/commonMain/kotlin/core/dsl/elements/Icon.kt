package core.dsl.elements

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import core.dsl.ConfigurationTagMaker
import core.dsl.elements.template.Element

@ConfigurationTagMaker
class Icon : Element {
    var icon: @Composable () -> Unit = { Image(painterResource("logo.svg"), null) }

    @Composable
    override fun render() {
        icon()
    }
}

