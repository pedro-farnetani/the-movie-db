package com.tmdb.features.movie.catalog.presentation.catalog

import app.cash.turbine.test
import com.tmdb.core.domain.movie.GetPopularMoviesUseCase
import com.tmdb.core.domain.movie.model.MovieDomainModel
import com.tmdb.core.domain.movie.model.mockList
import com.tmdb.core.navigation.routes.MovieDetailsRoute
import com.tmdb.features.movie.catalog.models.MovieUiModel
import com.tmdb.features.movie.catalog.models.mock
import com.tmdb.features.movie.catalog.presentation.catalog.MovieCatalogContracts.UiEffect
import com.tmdb.features.movie.catalog.presentation.mappers.MovieItemMapper
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MovieCatalogViewModelTest {

    @get:Rule
    val mockKRule: MockKRule = MockKRule(this)

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val getPopularMoviesUseCase: GetPopularMoviesUseCase = mockk()
    private val movieItemMapper: MovieItemMapper = mockk()

    @Before
    fun setUp() {
        every { movieItemMapper.mapToUiModel(any()) } returns MovieUiModel.mock()

        coEvery {
            getPopularMoviesUseCase()
        } returns Result.success(MovieDomainModel.mockList(10))
    }

    @Test
    fun `on view model initialization, assert that popular movies was fetched successfully`() =
        runTest {
            val viewModel = createViewModel()

            assertFalse(viewModel.uiState.value.isLoading)
            assertEquals(10, viewModel.uiState.value.movies.size)
        }

    @Test
    fun `on view model initialization, assert that popular movies fetch failure updates state correctly`() =
        runTest {
            coEvery { getPopularMoviesUseCase() } returns Result.failure(Exception("Network Error"))

            val viewModel = createViewModel()

            assertFalse(viewModel.uiState.value.isLoading)
            assertTrue(viewModel.uiState.value.isError)
        }

    @Test
    fun `on movie clicked, assert that navigate to movie details effect is emitted`() = runTest {
        val movie = MovieUiModel.mock()

        val viewModel = createViewModel()

        viewModel.effect.test {
            viewModel.sendEvent(MovieCatalogContracts.UiEvent.OnMovieClicked(movie))

            val expectedEffect = UiEffect.NavigateToMovieDetails(
                route = MovieDetailsRoute(
                    posterPath = movie.posterPath,
                    title = movie.title,
                    overview = movie.overview
                )
            )
            assertEquals(expectedEffect, awaitItem())
        }
    }

    @Test
    fun `on retry, assert that popular movies are fetched again`() = runTest {
        coEvery { getPopularMoviesUseCase() } returns Result.success(MovieDomainModel.mockList(5))

        val viewModel = createViewModel()

        viewModel.sendEvent(MovieCatalogContracts.UiEvent.OnRetry)

        coVerify(exactly = 2) { getPopularMoviesUseCase() }
        assertFalse(viewModel.uiState.value.isLoading)
        assertEquals(5, viewModel.uiState.value.movies.size)
    }

    private fun createViewModel() = MovieCatalogViewModel(
        getPopularMoviesUseCase = getPopularMoviesUseCase,
        movieItemMapper = movieItemMapper
    )
}
