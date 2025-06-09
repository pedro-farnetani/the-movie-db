package com.tmdb.features.movie.catalog.presentation.catalog

import com.tmdb.core.navigation.routes.MovieDetailsRoute
import com.tmdb.features.movie.catalog.models.MovieUiModel

object MovieCatalogContracts {

    data class UiState(
        val isLoading: Boolean = false,
        val movies: List<MovieUiModel> = emptyList(),
        val isError: Boolean = false
    )

    sealed interface UiEvent {
        data class OnMovieClicked(val movie: MovieUiModel) : UiEvent
        data object OnRetry : UiEvent
    }

    sealed interface UiEffect {
        data class NavigateToMovieDetails(val route: MovieDetailsRoute) : UiEffect
    }
}
