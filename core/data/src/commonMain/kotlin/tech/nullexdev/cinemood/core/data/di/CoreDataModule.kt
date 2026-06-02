package tech.nullexdev.cinemood.core.data.di

import tech.nullexdev.cinemood.core.data.repository.ThemeRepositoryImpl
import tech.nullexdev.cinemood.core.domain.repository.ThemeRepository
import org.koin.dsl.module

val coreDataModule = module {
    single<ThemeRepository> { ThemeRepositoryImpl() }
}
