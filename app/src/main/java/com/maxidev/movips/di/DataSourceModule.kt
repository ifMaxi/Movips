package com.maxidev.movips.di

import com.maxidev.movips.data.datasource.detail_datasource.CreditMovieDataSource
import com.maxidev.movips.data.datasource.detail_datasource.DetailedMovieDataSource
import com.maxidev.movips.data.remote.detail_remote.DetailsRemoteApiService
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