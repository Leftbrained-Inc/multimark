package core.dsl


import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.res.painterResource
import core.dsl.elements.Icon
import core.dsl.elements.template.Element
import core.dsl.elements.template.RootElement


@ConfigurationTagMaker
abstract class Configuration : Element {
    var icon: @Composable () -> Unit = { Image(painterResource("logo.svg"), null) }
    val LocalConfiguration = compositionLocalOf { this }

    @Composable
    override fun render() {
        CompositionLocalProvider(LocalConfiguration provides this) {
            icon()
        }
    }
}

expect abstract class ConfigurationPlatform() : Configuration {


    @Composable
    open fun render(onCloseRequest: () -> Unit)
}

class ConfigurationImpl : ConfigurationPlatform() {


    fun icon(init: @Composable () -> Unit) {
        icon = init
    }

}


fun config(init: ConfigurationImpl.() -> Unit): ConfigurationImpl {
    val config = ConfigurationImpl()
    config.init()
    return config
}

