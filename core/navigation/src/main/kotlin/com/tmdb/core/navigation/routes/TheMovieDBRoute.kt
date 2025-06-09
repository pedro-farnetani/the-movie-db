package com.tmdb.core.navigation.routes

import kotlinx.serialization.Serializable

@Serializable
sealed interface TheMovieDBRoute

@Serializable
data object MovieCatalogRoute : TheMovieDBRoute
