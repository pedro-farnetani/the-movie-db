package com.tmdb.features.movie.catalog.presentation.mappers

import com.tmdb.core.domain.movie.model.MovieDomainModel
import com.tmdb.features.movie.catalog.models.MovieUiModel
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.Locale
import javax.inject.Inject

internal class MovieItemMapper @Inject constructor() {

    fun mapToUiModel(domainModel: MovieDomainModel) = with(domainModel) {
        MovieUiModel(
            id = id,
            adult = adult,
            backdropPath = "$IMAGE_URL_BASE$backdropPath",
            genreIds = genreIds,
            originalLanguage = getLanguageName(originalLanguage),
            originalTitle = originalTitle,
            overview = overview,
            popularity = popularity,
            posterPath = "$IMAGE_URL_BASE$posterPath",
            releaseDate = releaseDate,
            title = title,
            video = video,
            voteAverage = formatVoteAverage(voteAverage),
            voteCount = voteCount
        )
    }

    private fun getLanguageName(languageCode: String): String {
        val locale = Locale(languageCode)
        return locale.displayName
    }

    private fun formatVoteAverage(voteAverage: Float): BigDecimal {
        return BigDecimal(voteAverage.toDouble()).setScale(1, RoundingMode.CEILING)
    }

    companion object {
        private const val IMAGE_URL_BASE = "https://image.tmdb.org/t/p/w500/"
    }
}
