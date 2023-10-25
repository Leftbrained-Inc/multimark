@file:Suppress("OPT_IN_USAGE")

fun allProjects(init: Project.() -> Unit) = configure(allprojects.filter { !it.path.contains("demos") }, init)



allProjects {
    repositories {
        google()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev/")
        mavenCentral()
    }
}

tasks.register("publishAndRun") {
    dependsOn("publishToMavenLocal")
    dependsOn(":desktop_test:run")
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

val exposedVersion: String by project

kotlin {
    jvm("desktop")
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material3)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                api(compose.components.resources)

                // addition libs
                api(libs.window.size)
                implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
                implementation("org.jetbrains.exposed:exposed-crypt:$exposedVersion")
                implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
                implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")

                implementation("org.jetbrains.exposed:exposed-jodatime:$exposedVersion")
                // or
                implementation("org.jetbrains.exposed:exposed-java-time:$exposedVersion")
                // or
                implementation("org.jetbrains.exposed:exposed-kotlin-datetime:$exposedVersion")

                implementation("org.jetbrains.exposed:exposed-json:$exposedVersion")
                implementation("org.jetbrains.exposed:exposed-money:$exposedVersion")
                implementation("org.jetbrains.exposed:exposed-spring-boot-starter:$exposedVersion")
                api(libs.appyx)
            }
        }

        val exposedVersion: String by project

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