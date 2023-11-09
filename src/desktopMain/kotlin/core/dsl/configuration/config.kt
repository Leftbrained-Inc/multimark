package core.dsl.configuration


actual fun config(init: ConfigurationBuilder.() -> Unit) {
    val builder = ConfigurationBuilder()
    builder.init()
    builder.build().render()
}