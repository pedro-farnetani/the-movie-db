package com.tmdb.core.domain.movie

import com.tmdb.core.data.movie.repository.MovieRepository
import com.tmdb.core.domain.movie.model.MovieDomainModel
import com.tmdb.core.domain.movie.model.toDomainModel
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {

    suspend operator fun invoke(): Result<List<MovieDomainModel>> {
        return movieRepository.getPopularMovies().fold(
            onSuccess = { movieDataModels ->
                Result.success(movieDataModels.map { it.toDomainModel() })
            },
            onFailure = { throwable ->
                Result.failure(throwable)
            }
        )
    }
}
