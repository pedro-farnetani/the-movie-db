package com.tmdb.core.domain.movie

import com.tmdb.core.data.movie.model.MovieDataModel
import com.tmdb.core.data.movie.model.mock
import com.tmdb.core.data.movie.repository.MovieRepository
import com.tmdb.core.domain.movie.model.MovieDomainModel
import com.tmdb.core.domain.movie.model.mock
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetPopularMoviesUseCaseTest {

    private val movieRepository: MovieRepository = mockk()

    private lateinit var useCase: GetPopularMoviesUseCase

    @Before
    fun setUp() {
        useCase = GetPopularMoviesUseCase(movieRepository)
    }

    @Test
    fun `on get popular movies with a successful repository response`() = runTest {
        coEvery {
            movieRepository.getPopularMovies()
        } returns Result.success(listOf(MovieDataModel.mock()))

        val result = useCase()

        val expectedResult = Result.success(listOf(MovieDomainModel.mock()))
        assertEquals(expectedResult, result)
    }

    @Test
    fun `on get popular movies with an unsuccessful repository response`() = runTest {
        val expectedException = Exception("Network error")
        coEvery {
            movieRepository.getPopularMovies()
        } returns Result.failure(expectedException)

        val result = useCase()

        val expectedResult = Result.failure<List<MovieDomainModel>>(expectedException)
        assertEquals(expectedResult, result)
    }
}
