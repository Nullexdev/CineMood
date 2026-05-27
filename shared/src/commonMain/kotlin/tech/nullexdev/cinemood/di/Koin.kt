package tech.nullexdev.cinemood.di

import tech.nullexdev.cinemood.service.data.iranianmoviesapi.di.iranianMoviesApiDataModule
import tech.nullexdev.cinemood.service.domain.usecase.GetMoviesUseCase
import tech.nullexdev.cinemood.service.domain.usecase.SearchMoviesUseCase
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

private val domainModule = module {
    factory<GetMoviesUseCase> {
        GetMoviesUseCase(
            get()
        )
    }
    factory<SearchMoviesUseCase> {
        SearchMoviesUseCase(
            get()
        )
    }
}

fun initKoin(appDeclaration: KoinAppDeclaration = {}) {
    startKoin {
        appDeclaration()
        modules(
            iranianMoviesApiDataModule,
            domainModule,
        )
    }
}
