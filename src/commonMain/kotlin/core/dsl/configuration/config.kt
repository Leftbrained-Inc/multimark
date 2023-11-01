package core.dsl.configuration

import core.configuration.ConfigurationImpl

/**
 * DSL конфигурации
 * @author Панков Вася (pank-su)
 */
fun config(init: ConfigurationBuilder.() -> Unit): ConfigurationImpl {
    val config = ConfigurationBuilder()
    config.init()
    return config.build()
}