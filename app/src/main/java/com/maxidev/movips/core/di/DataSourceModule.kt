package com.maxidev.movips.core.di

import com.maxidev.movips.detail.data.datasource.CreditMovieDataSource
import com.maxidev.movips.detail.data.datasource.DetailedMovieDataSource
import com.maxidev.movips.detail.data.remote.DetailsRemoteApiService
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
    fun providesDetailedDataSource(api: DetailsRemoteApiService): DetailedMovieDataSource =
        DetailedMovieDataSource(api)

    @Provides
    @Singleton
    fun providesCreditDataSource(api: DetailsRemoteApiService): CreditMovieDataSource =
        CreditMovieDataSource(api)
}