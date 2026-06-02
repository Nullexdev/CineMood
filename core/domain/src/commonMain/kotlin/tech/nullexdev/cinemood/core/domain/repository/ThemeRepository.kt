package tech.nullexdev.cinemood.core.domain.repository

import kotlinx.coroutines.flow.StateFlow
import tech.nullexdev.cinemood.core.domain.entity.ThemeMode

interface ThemeRepository {
    val themeMode: StateFlow<ThemeMode>
    suspend fun setThemeMode(mode: ThemeMode)
}
