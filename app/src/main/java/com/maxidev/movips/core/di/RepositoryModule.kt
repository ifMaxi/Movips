package com.maxidev.movips.core.di

import com.maxidev.movips.detail.data.repository.DetailedMovieRepository
import com.maxidev.movips.movies.data.repository.MoviesRepository
import com.maxidev.movips.detail.data.repository.impl.DetailedMovieRepositoryImpl
import com.maxidev.movips.movies.data.repository.impl.MoviesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindsDiscoverMovieRepository(
        repositoryImpl: MoviesRepositoryImpl
    ): MoviesRepository

    @Binds
    abstract fun bindsDetailMovieRepository(
        repositoryImpl: DetailedMovieRepositoryImpl
    ): DetailedMovieRepository
}