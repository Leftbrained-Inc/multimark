package core.dsl


import App
import androidx.compose.foundation.Image
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import core.dsl.elements.template.Element


val LocalConfiguration = compositionLocalOf {
    config {
    }
}

@ConfigurationTagMaker
abstract class Configuration : Element {
    var icon: @Composable (modifier: Modifier) -> Unit = { Image(painterResource("logo.svg"), null, modifier = it) }

    @Composable
    override fun render() {
        CompositionLocalProvider(LocalConfiguration provides this as ConfigurationImpl) {
            MaterialTheme {
                App()
            }
        }
    }
}

expect abstract class ConfigurationPlatform() : Configuration {


    @Composable
    open fun render(onCloseRequest: () -> Unit)
}

class ConfigurationImpl : ConfigurationPlatform() {

    var name = "test"
    fun icon(init: @Composable (modifier: Modifier) -> Unit) {
        icon = init
    }

}


fun config(init: ConfigurationImpl.() -> Unit): ConfigurationImpl {
    val config = ConfigurationImpl()
    config.init()
    return config
}

