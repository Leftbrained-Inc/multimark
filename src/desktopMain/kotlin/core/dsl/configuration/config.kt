package core.dsl.configuration

/**
 * DSL-функция создания конфигурации
 * @param init блок инициализации конфигурации
 * @author Василий Панков (pank-su)
 */
actual fun config(init: ConfigurationBuilder.() -> Unit) {
    val builder = ConfigurationBuilder()
    builder.init()
    builder.build().render()
}