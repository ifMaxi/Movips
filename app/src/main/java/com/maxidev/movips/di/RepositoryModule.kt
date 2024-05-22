package com.maxidev.movips.di

import com.maxidev.movips.data.repository.DetailedMovieRepository
import com.maxidev.movips.data.repository.MoviesRepository
import com.maxidev.movips.data.repository.impl.DetailedMovieRepositoryImpl
import com.maxidev.movips.data.repository.impl.MoviesRepositoryImpl
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