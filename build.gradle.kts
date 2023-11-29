fun allProjects(init: Project.() -> Unit) = configure(allprojects.filter { !it.path.contains("demos") }, init)



allProjects {
    repositories {
        google()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev/")
        mavenCentral()
        // Desktop target has to add this repo
        maven("https://jogamp.org/deployment/maven")
    }
}

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.maven.publish)
    alias(libs.plugins.compose)
}

allProjects {
    apply(plugin = "com.vanniktech.maven.publish")

    group = "com.leftbrained.multimark"
    version = extra["multimark-version"].toString()

    mavenPublishing {
        // signAllPublications()
        coordinates("com.leftbrained.multimark", this@allProjects.name, extra["multimark-version"].toString())
        pom {
            name.set(this@allProjects.name)
            description.set("")
            url.set("https://github.com/Leftbrained-Inc/multimark")

            licenses {
                license {
                    name.set("GNU General Public License v3.0")
                    url.set("https://github.com/Leftbrained-Inc/multimark/blob/master/LICENSE")
                }
            }
            developers {
                developer {
                    id.set("pank_su")
                    name.set("Pankov Vasya")
                    email.set("pank@pank.su")
                }
                developer {
                    id.set("bulkabuka")
                    name.set("Sergey Reinn")
                    email.set("byhelvig@icloud.com")
                }
            }
            scm {
                connection = "scm:git:git://github.com/Leftbrained-Inc/multimark.git"
                developerConnection = "scm:git:ssh://github.com/Leftbrained-Inc/multimark.git"
                url = "https://github.com/Leftbrained-Inc/multimark.git"
            }
        }
    }
}

kotlin {
    jvm("desktop")
    sourceSets {
        commonMain {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.materialIconsExtended)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)

                // exposed
                implementation(libs.exposed.core)
                implementation(libs.exposed.crypt)
                implementation(libs.exposed.dao)
                implementation(libs.exposed.jdbc)
                implementation(libs.exposed.kotlin.datetime)
                implementation(libs.exposed.json)
                implementation(libs.exposed.money)
                implementation(libs.sqlite.jdbc)
                implementation(libs.markdown.parser)

                // navigation
                implementation(libs.appyx.navigation)
                implementation(libs.appyx.interactions)
                api(libs.appyx.backstack)

                // koin
                implementation(libs.koin.core)
                implementation(libs.koin.compose)

                // other libs
                implementation(libs.file.picker)
                implementation(libs.kotlinx.io)

                implementation(libs.kotlinx.coroutines.core)

                api("io.github.kevinnzou:compose-webview-multiplatform:1.7.4")
            }
        }

        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.common)
                implementation(libs.kotlinx.coroutines.swing)
            }
        }
        commonTest {
            dependencies {
                implementation(compose.desktop.uiTestJUnit4)
                implementation(compose.desktop.currentOs)
            }
        }
    }
}