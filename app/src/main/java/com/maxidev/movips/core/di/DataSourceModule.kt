package com.maxidev.movips.core.di

import com.maxidev.movips.movies.data.remote.MoviesRemoteApiService
import com.maxidev.movips.detail.data.datasource.DetailedMovieDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun providesDetailedDataSource(api: MoviesRemoteApiService): DetailedMovieDataSource =
        DetailedMovieDataSource(api)
}