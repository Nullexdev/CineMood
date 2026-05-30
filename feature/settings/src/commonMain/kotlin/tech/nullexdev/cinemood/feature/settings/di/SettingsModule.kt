package tech.nullexdev.cinemood.feature.settings.di

import tech.nullexdev.cinemood.feature.settings.presentation.SettingsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val settingsModule = module {
    viewModelOf(::SettingsViewModel)
}
