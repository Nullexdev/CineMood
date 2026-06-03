package tech.nullexdev.cinemood.core.data.di

import org.koin.dsl.module
import tech.nullexdev.cinemood.core.data.db.DriverFactory
import tech.nullexdev.cinemood.core.data.db.AndroidDriverFactory
import org.koin.core.module.Module

actual fun platformModule(): Module = module {
    single<DriverFactory> { AndroidDriverFactory(get()) }
}
