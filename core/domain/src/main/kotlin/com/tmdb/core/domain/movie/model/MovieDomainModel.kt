package com.tmdb.core.domain.movie.model

import com.tmdb.core.data.movie.model.MovieDataModel

data class MovieDomainModel(
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

internal fun List<MovieDataModel>.toDomainModelList(): List<MovieDomainModel> {
    return map { it.toDomainModel() }
}

internal fun MovieDataModel.toDomainModel(): MovieDomainModel {
    return MovieDomainModel(
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

fun MovieDomainModel.Companion.mockList(size: Int = 10): List<MovieDomainModel> {
    return List(size) { index ->
        MovieDomainModel.mock(id = index.toLong())
    }
}

fun MovieDomainModel.Companion.mock(id: Long = 0) = MovieDomainModel(
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
