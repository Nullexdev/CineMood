package tech.nullexdev.cinemood.feature.favorite.presentation

import androidx.lifecycle.viewModelScope
import tech.nullexdev.cinemood.core.domain.presentation.mvi.MviViewModel
import tech.nullexdev.cinemood.feature.favorite.presentation.model.FavoriteMovieItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FavoriteViewModel : MviViewModel<FavoriteUiState, FavoriteUiAction>(
    initialState = FavoriteUiState(),
) {
    init {
        onAction(FavoriteUiAction.LoadFavorites)
    }
    override fun onAction(action: FavoriteUiAction) {
        when (action) {
            FavoriteUiAction.LoadFavorites, FavoriteUiAction.Refresh -> loadFavorites()
            is FavoriteUiAction.RemoveFavorite -> removeFavorite(action.movie)
        }
    }
    private fun loadFavorites() {
        viewModelScope.launch {
            updateState { copy(isLoading = true, errorMessage = null) }
            delay(300)
            updateState {
                copy(
                    isLoading = false,
                    favorites = placeholderFavorites(),
                    errorMessage = null,
                )
            }
        }
    }
    private fun removeFavorite(movie: FavoriteMovieItem) {
        updateState {
            copy(favorites = favorites.filterNot { it.id == movie.id })
        }
    }
    private fun placeholderFavorites(): List<FavoriteMovieItem> {
        return listOf(
            FavoriteMovieItem(id = 1, title = "Sample favorite 1"),
            FavoriteMovieItem(id = 2, title = "Sample favorite 2"),
        )
    }
}
