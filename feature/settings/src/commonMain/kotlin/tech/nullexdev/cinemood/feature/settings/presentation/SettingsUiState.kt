package tech.nullexdev.cinemood.feature.settings.presentation

import tech.nullexdev.cinemood.core.domain.presentation.mvi.MviUiState

data class SettingsUiState(
    val themeMode: SettingsThemeMode = SettingsThemeMode.SYSTEM,
    val notificationsEnabled: Boolean = true,
) : MviUiState
