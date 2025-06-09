package com.tmdb.features.movie.catalog.models

import com.tmdb.core.domain.movie.model.MovieDomainModel

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
    val voteAverage: Float,
    val voteCount: Int
) {
    companion object
}

internal fun List<MovieDomainModel>.toUiModelList(): List<MovieUiModel> {
    return map { it.toUiModel() }
}

internal fun MovieDomainModel.toUiModel(): MovieUiModel {
    return MovieUiModel(
        id = id,
        adult = adult,
        backdropPath = backdropPath,
        genreIds = genreIds,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
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
    voteAverage = 0f,
    voteCount = 0
)
