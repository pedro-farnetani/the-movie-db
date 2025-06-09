package com.tmdb.features.movie.catalog.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.tmdb.core.navigation.routes.MovieCatalogRoute
import com.tmdb.core.navigation.utils.fadeEnterTransition
import com.tmdb.core.navigation.utils.fadeExitTransition
import com.tmdb.features.movie.catalog.presentation.catalog.MovieCatalogScreen

fun NavGraphBuilder.movieCatalogScreen() {
    composable<MovieCatalogRoute>(
        enterTransition = { return@composable fadeEnterTransition() },
        popExitTransition = { return@composable fadeExitTransition() },
    ) {
        MovieCatalogScreen()
    }
}
