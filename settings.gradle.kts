rootProject.name = "multimark"


// include(":shared")
// include(":desktopApp")
include(":desktop_test")

project(":desktop_test").apply {
    projectDir = file("demos/desktop_test")
    name = "desktop_test"
}

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        mavenLocal()
        google()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}