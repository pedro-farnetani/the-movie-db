package com.tmdb.core.navigation.routes

import kotlinx.serialization.Serializable

@Serializable
sealed interface TheMovieDBRoute

@Serializable
data object MovieCatalogRoute : TheMovieDBRoute

@Serializable
data class MovieDetailsRoute(
    val posterPath: String,
    val title: String,
    val overview: String
) : TheMovieDBRoute
