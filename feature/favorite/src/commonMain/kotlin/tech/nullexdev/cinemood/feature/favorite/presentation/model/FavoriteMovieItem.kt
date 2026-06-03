package tech.nullexdev.cinemood.feature.favorite.presentation.model

data class FavoriteMovieItem(
    val id: Int,
    val title: String,
    val poster: String = "",
    val genres: List<String> = emptyList(),
)
