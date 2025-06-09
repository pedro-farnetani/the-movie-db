package com.tmdb.features.movie.catalog.models

import com.tmdb.core.domain.movie.model.MovieDomainModel
import java.math.BigDecimal

internal data class MovieUiModel(
    val id: Long,
    val adult: Boolean,
    val backdropPath: String,
    val genreIds: List<Int>,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: BigDecimal,
    val voteCount: Int
) {
    companion object
}

internal fun MovieUiModel.Companion.mockList(size: Int = 10): List<MovieUiModel> {
    return List(size) { index -> mock(id = index.toLong()) }
}

internal fun MovieUiModel.Companion.mock(id: Long = 0) = MovieUiModel(
    id = id,
    adult = false,
    backdropPath = "",
    genreIds = emptyList(),
    originalLanguage = "",
    originalTitle = "Original Title $id",
    overview = "",
    popularity = 0.0,
    posterPath = "",
    releaseDate = "",
    title = "Title $id",
    video = false,
    voteAverage = BigDecimal(0.0),
    voteCount = 0
)
