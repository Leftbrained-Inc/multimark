plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("maven-publish")
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {

            artifactId = "Multimark"
            // from(components["java"])
//            versionMapping {
//                usage("java-api") {
//                    fromResolutionOf("runtimeClasspath")
//                }
//                usage("java-runtime") {
//                    fromResolutionResult()
//                }
//            }
            pom {
                name.set("Multimark")
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
    repositories {
        maven {
            // change URLs to point to your repos, e.g. http://my.org/repo
            val releasesRepoUrl = uri(layout.buildDirectory.dir("repos/releases"))
            val snapshotsRepoUrl = uri(layout.buildDirectory.dir("repos/snapshots"))
            url = if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl
        }
    }
}

kotlin {

    jvm("desktop")


    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
            }
        }

        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.common)
            }
        }
    }
}
