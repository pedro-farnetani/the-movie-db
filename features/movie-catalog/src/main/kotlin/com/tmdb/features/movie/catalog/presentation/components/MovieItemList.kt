package com.tmdb.features.movie.catalog.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.tmdb.features.movie.catalog.R
import com.tmdb.features.movie.catalog.models.MovieUiModel
import com.tmdb.features.movie.catalog.models.mock

@Composable
internal fun MovieItemList(
    movie: MovieUiModel,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors().copy(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        elevation = CardDefaults.elevatedCardElevation(2.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(64.dp)
                    .clip(shape = RoundedCornerShape(32.dp)),
                model = movie.posterPath,
                contentScale = ContentScale.Crop,
                contentDescription = ""
            )

            Column {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(movie.originalLanguage)

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = movie.voteAverage.toString()
                    )

                    Image(
                        painter = painterResource(id = R.drawable.baseline_star_16),
                        contentDescription = ""
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun MovieItemListPreview() {
    MovieItemList(
        movie = MovieUiModel.mock(),
        onClick = {}
    )
}
