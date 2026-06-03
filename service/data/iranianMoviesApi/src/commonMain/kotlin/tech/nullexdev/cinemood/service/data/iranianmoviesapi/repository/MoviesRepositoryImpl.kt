package tech.nullexdev.cinemood.service.data.iranianmoviesapi.repository

import tech.nullexdev.cinemood.core.domain.common.BaseResult
import tech.nullexdev.cinemood.core.domain.common.toBaseResult
import tech.nullexdev.cinemood.core.data.common.toDomainError
import tech.nullexdev.cinemood.service.data.iranianmoviesapi.datasource.MoviesRemoteDataSource
import tech.nullexdev.cinemood.service.domain.moodel.MoviesPage
import tech.nullexdev.cinemood.service.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class MoviesRepositoryImpl(
    private val remoteDataSource: MoviesRemoteDataSource
) : MoviesRepository {

    override fun getMovies(page: Int): Flow<BaseResult<MoviesPage>> = flow {
        val result = remoteDataSource.fetchMovies(page)
        val baseResult = result.toBaseResult(
            errorMapper = { it.toDomainError().toUiMessage() }
        ) { responseDto ->
            responseDto.toDomainModel()
        }
        emit(baseResult)
    }

    override fun searchMovies(query: String, page: Int): Flow<BaseResult<MoviesPage>> = flow {
        // Input validation
        require(query.isNotBlank()) { "Search query cannot be blank" }
        require(page > 0) { "Page number must be positive, got $page" }

        println("Searching movies with query: '$query', page: $page")

        val result = remoteDataSource.searchMovies(query, page)
        val baseResult = result.toBaseResult(
            errorMapper = { it.toDomainError().toUiMessage() }
        ) { responseDto ->
            responseDto.toDomainModel()
        }

        // Optional: Log search results
        if (baseResult is BaseResult.Success) {
            println("Search completed: found ${baseResult.data} movies")
        } else if (baseResult is BaseResult.Error) {
            println("Search failed: ${baseResult.message}")
        }

        emit(baseResult)
    }.catch { exception ->
        emit(BaseResult.Error(
            exception = exception as? Exception ?: Exception(exception),
            message = exception.toDomainError().toUiMessage()
        ))
    }
}
