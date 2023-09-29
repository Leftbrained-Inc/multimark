@file:Suppress("OPT_IN_USAGE")

fun allProjects(init: Project.() -> Unit) = configure(allprojects.filter { !it.path.contains("demos") }, init)


allProjects {
    repositories {
        google()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev/")
        mavenCentral()
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


kotlin{
    jvm("desktop")
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material3)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                api(compose.components.resources)
            }
        }

        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.common)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(compose.desktop.uiTestJUnit4)
                implementation(compose.desktop.currentOs)
            }
        }
    }
}