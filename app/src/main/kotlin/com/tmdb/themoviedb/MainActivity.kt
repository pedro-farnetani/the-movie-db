package com.tmdb.themoviedb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.tmdb.core.navigation.routes.MovieCatalogRoute
import com.tmdb.designsystem.theme.TheMovieDBTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TheMovieDBTheme {
                Column(modifier = Modifier.fillMaxSize()) {
                    TheMovieDBNavHost(
                        startDestination = MovieCatalogRoute
                    )
                }
            }
        }
    }
}
