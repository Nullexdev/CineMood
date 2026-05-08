package com.alimmzdev.conemood_kmp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "CineMookKmp",
    ) {
        App()
    }
}