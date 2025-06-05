package com.tmdb.core.data.movie.repository

import com.tmdb.core.data.movie.api.MovieApi
import com.tmdb.core.data.movie.model.MovieDataModel
import com.tmdb.core.data.movie.model.toDataModelList
import javax.inject.Inject

internal class MovieRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi
) : MovieRepository {

    override suspend fun getPopularMovies(): Result<List<MovieDataModel>> {
        return runCatching {
            movieApi.getPopularMovies()
        }.fold(
            onSuccess = { response ->
                Result.success(response.movieListDto.toDataModelList())
            },
            onFailure = { throwable ->
                Result.failure(throwable)
            }
        )
    }
}
