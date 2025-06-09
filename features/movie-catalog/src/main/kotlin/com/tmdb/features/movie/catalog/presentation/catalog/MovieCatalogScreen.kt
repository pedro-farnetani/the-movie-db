package com.tmdb.features.movie.catalog.presentation.catalog

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tmdb.core.navigation.routes.MovieDetailsRoute
import com.tmdb.designsystem.components.LoadingScreen
import com.tmdb.features.movie.catalog.R
import com.tmdb.features.movie.catalog.models.MovieUiModel
import com.tmdb.features.movie.catalog.models.mockList
import com.tmdb.features.movie.catalog.presentation.catalog.MovieCatalogContracts.UiEvent
import com.tmdb.features.movie.catalog.presentation.components.MovieItemList
import kotlinx.coroutines.flow.SharedFlow

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.MovieCatalogScreen(
    animatedVisibilityScope: AnimatedVisibilityScope,
    onMovieClicked: (MovieDetailsRoute) -> Unit,
    viewModel: MovieCatalogViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val context = LocalContext.current

    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            TopAppBar(
                modifier = Modifier.shadow(elevation = 2.dp),
                title = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(id = R.string.movie_catalog_title),
                        textAlign = TextAlign.Center
                    )
                }
            )
        },
        content = { innerPadding ->
            if (uiState.isLoading) {
                LoadingScreen()
            } else {
                MovieCatalogContent(
                    modifier = Modifier.padding(innerPadding),
                    movies = uiState.movies,
                    animatedVisibilityScope = animatedVisibilityScope,
                    onMovieClicked = { movie ->
                        viewModel.sendEvent(UiEvent.OnMovieClicked(movie))
                    }
                )
            }
        }
    )

    LaunchedEffect(uiState.isError) {
        if (uiState.isError) {
            snackbarHostState.showSnackbar(
                message = context.getString(R.string.error_generic_message),
                actionLabel = context.getString(R.string.action_retry)
            )
        }
    }

    EffectHandler(
        effect = viewModel.effect,
        onMovieClicked = onMovieClicked
    )
}

@Composable
private fun EffectHandler(
    effect: SharedFlow<MovieCatalogContracts.UiEffect>,
    onMovieClicked: (MovieDetailsRoute) -> Unit
) {
    LaunchedEffect(Unit) {
        effect.collect { uiEffect ->
            when (uiEffect) {
                is MovieCatalogContracts.UiEffect.NavigateToMovieDetails -> {
                    onMovieClicked(uiEffect.route)
                }
            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun SharedTransitionScope.MovieCatalogContent(
    movies: List<MovieUiModel>,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onMovieClicked: (MovieUiModel) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(count = movies.size, key = { index -> movies[index].id }) { index ->
            val movie = movies[index]
            MovieItemList(
                movie = movie,
                animatedVisibilityScope = animatedVisibilityScope,
                onClick = { onMovieClicked(movie) }
            )
        }
    }
}

@SuppressLint("UnusedSharedTransitionModifierParameter")
@OptIn(ExperimentalSharedTransitionApi::class)
@Preview
@Composable
private fun MovieCatalog() {
    SharedTransitionScope {
        AnimatedVisibility(visible = true) {
            MovieCatalogContent(
                movies = MovieUiModel.mockList(),
                animatedVisibilityScope = this,
                onMovieClicked = {}
            )
        }
    }
}
