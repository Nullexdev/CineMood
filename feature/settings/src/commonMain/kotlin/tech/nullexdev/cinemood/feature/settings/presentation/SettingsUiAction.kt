package tech.nullexdev.cinemood.feature.settings.presentation

import tech.nullexdev.cinemood.core.domain.presentation.mvi.MviUiAction

sealed interface SettingsUiAction : MviUiAction {
    data class ThemeModeSelected(val themeMode: SettingsThemeMode) : SettingsUiAction
    data class NotificationsToggled(val enabled: Boolean) : SettingsUiAction
}
