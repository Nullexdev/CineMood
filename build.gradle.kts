plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.composeHotReload) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.androidMultiplatformLibrary) apply false
    alias(libs.plugins.androidLint) apply false
    alias(libs.plugins.jetbrainsKotlinJvm) apply false
    alias(libs.plugins.kotlinxSerialization) apply false
}

val wasmYarnLockPath: String = "${rootProject.layout.buildDirectory.get().asFile.absolutePath}/wasm/yarn.lock"

tasks.matching { task -> task.name == "kotlinWasmStoreYarnLock" }.configureEach {
    doFirst {
        val wasmYarnLockFile = java.io.File(wasmYarnLockPath)
        if (!wasmYarnLockFile.parentFile.exists()) {
            wasmYarnLockFile.parentFile.mkdirs()
        }
        if (!wasmYarnLockFile.exists()) {
            wasmYarnLockFile.writeText("")
        }
    }
}