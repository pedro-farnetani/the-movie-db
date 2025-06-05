package com.tmdb.core.data.movie.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class MovieResponseDto(
    @Json(name = "results")
    val movieListDto: List<MovieDto>,
    val page: Int,
    val totalPages: Int = 0,
    val totalResults: Int = 0
) {
    companion object
}

internal fun MovieResponseDto.Companion.mock() = MovieResponseDto(
    movieListDto = listOf(MovieDto.mock()),
    page = 0,
    totalPages = 0,
    totalResults = 0
)
