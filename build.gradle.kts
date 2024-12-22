plugins {
    alias(libs.plugins.dokka)
    alias(libs.plugins.gitSemVer)
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.qa)
    alias(libs.plugins.publishOnCentral)
    alias(libs.plugins.multiJvmTesting)
    alias(libs.plugins.taskTree)
}

group = "it.unibo.collektive"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.kotlin.stdlib)
    testImplementation(libs.bundles.kotlin.testing)
}

kotlin {
    compilerOptions {
        allWarningsAsErrors = true
        freeCompilerArgs = listOf("-opt-in=kotlin.RequiresOptIn")
    }
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        showStandardStreams = true
        showCauses = true
        showStackTraces = true
        events(
            *org.gradle.api.tasks.testing.logging.TestLogEvent
                .values(),
        )
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
    }
}

signing {
    val signingKey: String? by project
    val signingPassword: String? by project
    useInMemoryPgpKeys(signingKey, signingPassword)
}

publishOnCentral {
    projectUrl = "https://github.com/Collektive/${rootProject.name}"
    projectLongName = "collektivize"
    projectDescription = "Creates the fileded version of each methods in the Kotlin stdlib"
    licenseName = "Apache License 2.0"
    licenseUrl = "https://opensource.org/license/Apache-2.0/"
    publishing {
        publications {
            withType<MavenPublication> {
                pom {
                    developers {
                        developer {
                            name = "Nicolas Farabegoli"
                            email = "nicolas.farabegoli@gmail.com"
                            url = "https://nicolasfarabegoli.it"
                        }
                        developer {
                            name = "Danilo Pianini"
                            email = "danilo.pianini@gmail.com"
                            url = "http://www.danilopianini.org/"
                        }
                    }
                }
            }
        }
    }
}
