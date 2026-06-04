package tech.nullexdev.cinemood.core.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import platform.UIKit.UIApplication
import platform.UIKit.UIStatusBarStyleDarkContent
import platform.UIKit.UIStatusBarStyleLightContent
import platform.UIKit.setStatusBarStyle

@Composable
actual fun SystemAppearance(
    isLight: Boolean,
    statusBarColor: Color,
    navigationBarColor: Color
) {
    SideEffect {
        UIApplication.sharedApplication.setStatusBarStyle(
            if (isLight) UIStatusBarStyleDarkContent else UIStatusBarStyleLightContent
        )
    }
}
