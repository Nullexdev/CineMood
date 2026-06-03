package tech.nullexdev.cinemood.feature.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import tech.nullexdev.cinemood.core.presentation.components.MovieCard
import tech.nullexdev.cinemood.feature.favorite.presentation.FavoriteViewModel
import tech.nullexdev.cinemood.service.domain.moodel.Movie
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel = koinViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val gridState = rememberLazyGridState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .statusBarsPadding(),
    ) {
        when {
            uiState.isLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            else -> {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 300.dp),
                    state = gridState,
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    item(span = { GridItemSpan(maxLineSpan) }) {
                        Column {
                            Text(
                                text = "Favorites",
                                style = MaterialTheme.typography.headlineMedium,
                                color = MaterialTheme.colorScheme.onSurface,
                            )
                            Text(
                                text = "${uiState.favorites.size} saved",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                            uiState.errorMessage?.let { message ->
                                Text(
                                    text = message,
                                    color = MaterialTheme.colorScheme.error,
                                    modifier = Modifier.padding(top = 8.dp)
                                )
                            }
                        }
                    }

                    items(uiState.favorites, key = { it.id }) { favorite ->
                        MovieCard(
                            movie = Movie(
                                id = favorite.id,
                                title = favorite.title,
                                poster = favorite.poster,
                                genres = favorite.genres,
                                images = emptyList()
                            )
                        )
                    }
                }
            }
        }
    }
}
