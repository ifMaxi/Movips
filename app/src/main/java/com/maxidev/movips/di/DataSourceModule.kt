package com.maxidev.movips.di

import com.maxidev.movips.data.remote.RemoteApiService
import com.maxidev.movips.data.repository.datasource.DetailedMovieDataSource
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
    fun providesDetailedDataSource(api: RemoteApiService): DetailedMovieDataSource =
        DetailedMovieDataSource(api)
}