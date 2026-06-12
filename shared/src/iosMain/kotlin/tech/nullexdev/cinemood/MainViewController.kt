package tech.nullexdev.cinemood

import androidx.compose.ui.window.ComposeUIViewController
import tech.nullexdev.cinemood.di.initKoin

fun MainViewController() = ComposeUIViewController {
    initKoin()
    App()
}
