package tech.nullexdev.cinemood.feature.settings.presentation

import tech.nullexdev.cinemood.core.domain.presentation.mvi.MviViewModel

class SettingsViewModel : MviViewModel<SettingsUiState, SettingsUiAction>(
    initialState = SettingsUiState(),
) {
    override fun onAction(action: SettingsUiAction) {
        when (action) {
            is SettingsUiAction.ThemeModeSelected -> updateState { copy(themeMode = action.themeMode) }
            is SettingsUiAction.NotificationsToggled -> updateState { copy(notificationsEnabled = action.enabled) }
        }
    }
}
