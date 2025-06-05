package com.tmdb.core.data.movie.di

import com.tmdb.core.data.movie.api.MovieApi
import com.tmdb.core.data.movie.repository.MovieRepository
import com.tmdb.core.data.movie.repository.MovieRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface DataMovieModule {

    @Binds
    @Singleton
    fun bindMovieRepository(repository: MovieRepositoryImpl): MovieRepository

    companion object {

        @Provides
        @Singleton
        fun provideMovieApi(
            retrofit: Retrofit
        ): MovieApi {
            return retrofit.create(MovieApi::class.java)
        }
    }
}
