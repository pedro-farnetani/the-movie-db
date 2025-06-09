package com.tmdb.features.movie.catalog.presentation.catalog

import com.tmdb.features.movie.catalog.models.MovieUiModel

internal object MovieCatalogContracts {

    data class UiState(
        val isLoading: Boolean = false,
        val movies: List<MovieUiModel> = emptyList()
    )

    sealed interface UiEvent {
        data class OnMovieClicked(val movie: MovieUiModel) : UiEvent
        data object OnRetry : UiEvent
    }

    sealed interface UiEffect {
        data class NavigateToMovieDetails(val movie: MovieUiModel) : UiEffect
        data object ShowError : UiEffect
    }
}
