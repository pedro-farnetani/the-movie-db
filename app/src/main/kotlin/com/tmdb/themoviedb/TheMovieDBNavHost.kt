package com.tmdb.themoviedb

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.tmdb.core.navigation.routes.TheMovieDBRoute
import com.tmdb.features.movie.catalog.navigation.movieCatalogScreen

@Composable
internal fun TheMovieDBNavHost(
    startDestination: TheMovieDBRoute,
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        movieCatalogScreen()
    }
}
