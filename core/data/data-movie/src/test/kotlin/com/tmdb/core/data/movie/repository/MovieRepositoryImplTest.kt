package com.tmdb.core.data.movie.repository

import com.tmdb.core.data.movie.api.MovieApi
import com.tmdb.core.data.movie.api.model.MovieResponseDto
import com.tmdb.core.data.movie.api.model.mock
import com.tmdb.core.data.movie.model.MovieDataModel
import com.tmdb.core.data.movie.model.mock
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MovieRepositoryImplTest {

    private val movieApi: MovieApi = mockk()

    private lateinit var repository: MovieRepository

    @Before
    fun setUp() {
        repository = MovieRepositoryImpl(movieApi)
    }

    @Test
    fun `on getPopularMovies with a successful api response`() = runTest {
        coEvery { movieApi.getPopularMovies() } returns MovieResponseDto.mock()

        val result = repository.getPopularMovies()

        val expectedResult = Result.success(listOf(MovieDataModel.mock()))
        assertEquals(expectedResult, result)
    }

    @Test
    fun `on getPopularMovies with an unsuccessful api response`() = runTest {
        val expectedException = Exception("Network error")
        coEvery { movieApi.getPopularMovies() } throws expectedException

        val result = repository.getPopularMovies()

        val expectedResult = Result.failure<List<MovieDataModel>>(expectedException)
        assertEquals(expectedResult, result)
    }
}
