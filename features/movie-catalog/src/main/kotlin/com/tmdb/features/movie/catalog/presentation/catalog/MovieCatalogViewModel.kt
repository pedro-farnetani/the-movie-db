package com.tmdb.features.movie.catalog.presentation.catalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tmdb.core.domain.movie.GetPopularMoviesUseCase
import com.tmdb.core.navigation.routes.MovieDetailsRoute
import com.tmdb.features.movie.catalog.models.MovieUiModel
import com.tmdb.features.movie.catalog.presentation.catalog.MovieCatalogContracts.UiEffect
import com.tmdb.features.movie.catalog.presentation.catalog.MovieCatalogContracts.UiEvent
import com.tmdb.features.movie.catalog.presentation.catalog.MovieCatalogContracts.UiState
import com.tmdb.features.movie.catalog.presentation.mappers.MovieItemMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieCatalogViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val movieItemMapper: MovieItemMapper
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState(isLoading = true))
    val uiState = _uiState.asStateFlow()

    private val _effect = MutableSharedFlow<UiEffect>()
    val effect = _effect.asSharedFlow()

    init {
        fetchPopularMovies()
    }

    fun sendEvent(event: UiEvent) {
        when (event) {
            is UiEvent.OnMovieClicked -> onMovieClicked(event.movie)
            UiEvent.OnRetry -> onRetry()
        }
    }

    private fun fetchPopularMovies() {
        viewModelScope.launch {
            getPopularMoviesUseCase().fold(
                onSuccess = { movies ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            movies = movies.map { movieDomainData ->
                                movieItemMapper.mapToUiModel(movieDomainData)
                            }
                        )
                    }
                },
                onFailure = {
                    _uiState.update { it.copy(isLoading = false, isError = true) }
                }
            )
        }
    }

    private fun onMovieClicked(movie: MovieUiModel) {
        viewModelScope.launch {
            val route = MovieDetailsRoute(
                posterPath = movie.posterPath,
                title = movie.title,
                overview = movie.overview
            )
            _effect.emit(UiEffect.NavigateToMovieDetails(route))
        }
    }

    private fun onRetry() {
        _uiState.update { it.copy(isLoading = true) }
        fetchPopularMovies()
    }
}
