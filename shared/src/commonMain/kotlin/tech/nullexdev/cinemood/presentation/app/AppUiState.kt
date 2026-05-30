package tech.nullexdev.cinemood.presentation.app

import tech.nullexdev.cinemood.core.domain.presentation.mvi.MviUiState
import tech.nullexdev.cinemood.core.navigation.Screen
import tech.nullexdev.cinemood.theme.ThemeState

data class AppUiState(
    val currentScreen: Screen = Screen.Home,
    val themeMode: ThemeState.ThemeMode = ThemeState.ThemeMode.SYSTEM,
) : MviUiState
