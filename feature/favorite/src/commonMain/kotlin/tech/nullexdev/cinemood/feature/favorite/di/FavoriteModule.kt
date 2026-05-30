package tech.nullexdev.cinemood.feature.favorite.di

import tech.nullexdev.cinemood.feature.favorite.presentation.FavoriteViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val favoriteModule = module {
    viewModelOf(::FavoriteViewModel)
}
