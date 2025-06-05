package com.tmdb.core.data.movie.model

import com.tmdb.core.data.movie.api.model.MovieDto

data class MovieDataModel(
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

internal fun List<MovieDto>.toDataModelList(): List<MovieDataModel> {
    return map { it.toDataModel() }
}

internal fun MovieDto.toDataModel(): MovieDataModel {
    return MovieDataModel(
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

internal fun MovieDataModel.Companion.mock(id: Long = 0) = MovieDataModel(
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
