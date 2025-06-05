package com.tmdb.core.data.movie.repository

import com.tmdb.core.data.movie.model.MovieDataModel

interface MovieRepository {

    suspend fun getPopularMovies(): Result<List<MovieDataModel>>
}
