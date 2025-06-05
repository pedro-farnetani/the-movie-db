package com.tmdb.core.data.movie.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class MovieDto(
    val id: Long,
    val adult: Boolean,
    @Json(name = "backdrop_path")
    val backdropPath: String,
    @Json(name = "genre_ids")
    val genreIds: List<Int>,
    @Json(name = "original_language")
    val originalLanguage: String,
    @Json(name = "original_title")
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    @Json(name = "poster_path")
    val posterPath: String,
    @Json(name = "release_date")
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    @Json(name = "vote_average")
    val voteAverage: Float,
    @Json(name = "vote_count")
    val voteCount: Int
) {
    companion object
}

internal fun MovieDto.Companion.mock(id: Long = 0) = MovieDto(
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
