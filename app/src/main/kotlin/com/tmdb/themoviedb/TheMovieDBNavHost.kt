package com.tmdb.themoviedb

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.tmdb.core.navigation.routes.MovieCatalogRoute
import com.tmdb.core.navigation.routes.MovieDetailsRoute
import com.tmdb.core.navigation.routes.TheMovieDBRoute
import com.tmdb.core.navigation.utils.fadeEnterTransition
import com.tmdb.core.navigation.utils.fadeExitTransition
import com.tmdb.features.movie.catalog.presentation.catalog.MovieCatalogScreen
import com.tmdb.features.movie.catalog.presentation.components.MovieDetails

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
internal fun SharedTransitionScope.TheMovieDBNavHost(
    startDestination: TheMovieDBRoute,
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable<MovieCatalogRoute>(
            enterTransition = { return@composable fadeEnterTransition() },
            popExitTransition = { return@composable fadeExitTransition() },
        ) {
            MovieCatalogScreen(
                animatedVisibilityScope = this,
                onMovieClicked = { route ->
                    navController.navigate(
                        MovieDetailsRoute(
                            posterPath = route.posterPath,
                            title = route.title,
                            overview = route.overview
                        )
                    )
                }
            )
        }

        composable<MovieDetailsRoute> { backStackEntry ->
            val route: MovieDetailsRoute = backStackEntry.toRoute()

            MovieDetails(
                posterPath = route.posterPath,
                title = route.title,
                overview = route.overview,
                animatedVisibilityScope = this,
                onBackClicked = navController::navigateUp
            )
        }
    }
}
