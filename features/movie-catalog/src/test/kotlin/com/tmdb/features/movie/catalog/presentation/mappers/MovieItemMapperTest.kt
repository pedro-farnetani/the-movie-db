package com.tmdb.features.movie.catalog.presentation.mappers

import com.tmdb.core.domain.movie.model.MovieDomainModel
import com.tmdb.core.domain.movie.model.mock
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.util.Locale

class MovieItemMapperTest {

    private val movieItemMapper = MovieItemMapper()

    private val currentLocale = Locale.getDefault()

    @Before
    fun setUp() {
        Locale.setDefault(Locale.US)
    }

    @After
    fun tearDown() {
        Locale.setDefault(currentLocale)
    }

    @Test
    fun `test mapping of MovieDomainModel to MovieUiModel`() {
        val domainModel = MovieDomainModel.mock().copy(
            originalLanguage = "en",
            voteAverage = 1.234f
        )

        val uiModel = movieItemMapper.mapToUiModel(domainModel)

        with(uiModel) {
            assertEquals(domainModel.id, id)
            assertEquals(domainModel.adult, adult)
            assertEquals("https://image.tmdb.org/t/p/w500/${domainModel.backdropPath}", backdropPath)
            assertEquals(domainModel.genreIds, genreIds)
            assertEquals("English", originalLanguage)
            assertEquals(domainModel.originalTitle, originalTitle)
            assertEquals(domainModel.overview, overview)
            assertEquals(domainModel.popularity, popularity, 0.0)
            assertEquals("https://image.tmdb.org/t/p/w500/${domainModel.posterPath}", posterPath)
            assertEquals(domainModel.releaseDate, releaseDate)
            assertEquals(domainModel.title, title)
            assertEquals(domainModel.video, video)
            assertEquals("1.3", voteAverage.toString())
            assertEquals(domainModel.voteCount, voteCount)
        }
    }
}
