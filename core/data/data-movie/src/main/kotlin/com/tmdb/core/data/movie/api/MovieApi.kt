package com.tmdb.core.data.movie.api

import com.tmdb.core.data.movie.api.model.MovieResponseDto
import retrofit2.http.GET

internal interface MovieApi {

    @GET("/3/movie/popular")
    suspend fun getPopularMovies(): MovieResponseDto
}
