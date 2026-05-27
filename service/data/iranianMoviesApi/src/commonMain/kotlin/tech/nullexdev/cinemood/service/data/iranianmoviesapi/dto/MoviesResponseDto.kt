package tech.nullexdev.cinemood.service.data.iranianmoviesapi.dto

import tech.nullexdev.cinemood.core.data.network.dto.DomainConvertible
import tech.nullexdev.cinemood.service.domain.moodel.MoviesPage
import kotlinx.serialization.Serializable

@Serializable
data class MoviesResponseDto(
    val data: List<MovieDto>,
    val metadata: MetadataDto
) : DomainConvertible<MoviesPage> {
    override fun toDomainModel(): MoviesPage =
        MoviesPage(
            movies = data.map { it.toDomainModel() },
            currentPage = metadata.current_page,
            perPage = metadata.per_page,
            pageCount = metadata.page_count,
            totalCount = metadata.total_count
        )
}