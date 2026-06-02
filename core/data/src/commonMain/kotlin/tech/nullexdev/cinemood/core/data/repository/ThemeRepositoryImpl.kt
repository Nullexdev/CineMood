package tech.nullexdev.cinemood.core.data.repository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import tech.nullexdev.cinemood.core.domain.entity.ThemeMode
import tech.nullexdev.cinemood.core.domain.repository.ThemeRepository

class ThemeRepositoryImpl : ThemeRepository {
    private val _themeMode = MutableStateFlow(ThemeMode.SYSTEM)
    override val themeMode: StateFlow<ThemeMode> = _themeMode.asStateFlow()

    override suspend fun setThemeMode(mode: ThemeMode) {
        _themeMode.value = mode
    }
}
