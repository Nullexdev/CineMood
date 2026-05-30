package tech.nullexdev.cinemood.feature.home.di

import tech.nullexdev.cinemood.feature.home.presentation.HomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val homeModule = module {
    viewModelOf(::HomeViewModel)
}
