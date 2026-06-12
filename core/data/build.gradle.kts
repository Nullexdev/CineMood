import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidMultiplatformLibrary)
    alias(libs.plugins.androidLint)
    alias(libs.plugins.ksp)
    alias(libs.plugins.androidx.room)
    alias(libs.plugins.sqldelight)
}

kotlin {
    // 1. Android Target Configuration
    android {
        namespace = "tech.nullexdev.cinemood.core.data"
        compileSdk = 37
        minSdk = 24

        withHostTestBuilder {
            // Configure host tests if needed
        }

        withDeviceTestBuilder {
            sourceSetTreeName = "test"
        }.configure {
            instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }
    }

    val xcfName = "core-dataKit"

    iosArm64 {
        binaries.framework {
            baseName = xcfName
        }
    }

    iosSimulatorArm64 {
        binaries.framework {
            baseName = xcfName
        }
    }

    jvm()

    js {
        browser()
        binaries.executable()
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
        binaries.executable()
    }

    sourceSets {
        val webMain by creating {
            dependsOn(commonMain.get())
            dependencies {
                implementation(libs.sqldelight.web.worker.driver)
                implementation(devNpm("sql.js", "1.12.0"))
                implementation(libs.wrappers.browser)
            }
        }

        val nativeJvmRoomMain by creating {
            dependsOn(commonMain.get())
            dependencies {
                implementation(libs.androidx.room.runtime)
                implementation(libs.androidx.sqlite.bundled)
            }
        }

        commonMain {
            dependencies {
                implementation(project(":core:domain"))
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.content.negotiation)
                implementation(libs.ktor.serialization.kotlinx.json)
                implementation(libs.ktor.client.logging)
                implementation(libs.koin.core)
                implementation(libs.sqldelight.coroutines.extensions)
            }
        }

        androidMain {
            dependsOn(nativeJvmRoomMain)
            dependencies {
                implementation(libs.ktor.client.okhttp)
                implementation(libs.okhttp.logging.interceptor)
                implementation(libs.chucker)
                implementation(libs.sqldelight.android.driver)
            }
        }

        iosMain {
            dependsOn(nativeJvmRoomMain) // Connects Room safely
            dependencies {
                implementation(libs.ktor.client.darwin)
                implementation(libs.sqldelight.ios.driver)
            }
        }

        val iosArm64Main by getting {
            dependsOn(iosMain.get())
        }
        val iosSimulatorArm64Main by getting {
            dependsOn(iosMain.get())
        }

        jvmMain {
            dependsOn(nativeJvmRoomMain)
            dependencies {
                implementation("io.ktor:ktor-client-java:${libs.versions.ktor.get()}")
                implementation(libs.sqldelight.sqlite.driver)
            }
        }

        jsMain {
            dependsOn(webMain)
            dependencies {
                implementation("io.ktor:ktor-client-js:${libs.versions.ktor.get()}")
            }
        }

        wasmJsMain {
            dependsOn(webMain)
            dependencies {
                implementation("io.ktor:ktor-client-js:${libs.versions.ktor.get()}")
            }
        }
    }
}

dependencies {
    add("kspAndroid", libs.androidx.room.compiler)
    add("kspIosArm64", libs.androidx.room.compiler)
    add("kspIosSimulatorArm64", libs.androidx.room.compiler)
    add("kspJvm", libs.androidx.room.compiler)
}

room {
    schemaDirectory("$projectDir/schemas")
}

sqldelight {
    databases {
        create("CineMoodDatabase") {
            packageName.set("tech.nullexdev.cinemood.core.data.db")
        }
    }
}