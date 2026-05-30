package tech.nullexdev.cinemood.feature.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import tech.nullexdev.cinemood.feature.search.presentation.SearchUiAction
import tech.nullexdev.cinemood.feature.search.presentation.SearchViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = koinViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text(
            text = "Search",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
        )
        OutlinedTextField(
            value = uiState.query,
            onValueChange = { viewModel.onAction(SearchUiAction.QueryChanged(it)) },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Movie title") },
            singleLine = true,
        )
        Button(
            onClick = { viewModel.onAction(SearchUiAction.SearchSubmitted) },
            enabled = !uiState.isLoading,
        ) {
            Text("Search")
        }
        Button(onClick = { viewModel.onAction(SearchUiAction.ClearQuery) }) {
            Text("Clear")
        }
        uiState.errorMessage?.let { message ->
            Text(text = message, color = MaterialTheme.colorScheme.error)
        }
        if (uiState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        }
        if (uiState.hasSearched && !uiState.isLoading) {
            Text(
                text = "${uiState.movies.size} results",
                color = MaterialTheme.colorScheme.onSecondaryContainer,
            )
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(uiState.movies, key = { it.id }) { movie ->
                    Text(
                        text = movie.title,
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                    )
                }
            }
        }
    }
}
