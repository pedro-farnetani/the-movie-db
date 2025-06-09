package com.tmdb.features.movie.catalog.presentation.catalog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tmdb.designsystem.components.LoadingScreen
import com.tmdb.features.movie.catalog.R
import com.tmdb.features.movie.catalog.models.MovieUiModel
import com.tmdb.features.movie.catalog.presentation.catalog.MovieCatalogContracts.UiEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MovieCatalogScreen(
    viewModel: MovieCatalogViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

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
                    onMovieClicked = { movie ->
                        viewModel.sendEvent(UiEvent.OnMovieClicked(movie))
                    }
                )
            }
        }
    )
}

@Composable
private fun MovieCatalogContent(
    movies: List<MovieUiModel>,
    onMovieClicked: (MovieUiModel) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(count = movies.size, key = { index -> movies[index].id }) { index ->
            val movie = movies[index]
            MovieItem(
                movie = movie,
                onClick = { onMovieClicked(movie) }
            )
        }
    }
}

@Composable
private fun MovieItem(
    movie: MovieUiModel,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(16.dp)
    ) {
        Text(movie.title)

        Text(movie.originalLanguage)
    }
}
