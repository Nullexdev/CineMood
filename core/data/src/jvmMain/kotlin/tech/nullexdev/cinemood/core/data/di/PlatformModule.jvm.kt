package tech.nullexdev.cinemood.core.data.di

import org.koin.dsl.module
import tech.nullexdev.cinemood.core.data.db.DriverFactory
import tech.nullexdev.cinemood.core.data.db.JvmDriverFactory
import org.koin.core.module.Module

actual fun platformModule(): Module = module {
    single<DriverFactory> { JvmDriverFactory() }
}
