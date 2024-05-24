package com.maxidev.movips.core.di

import com.maxidev.movips.detail.data.remote.DetailsRemoteApiService
import com.maxidev.movips.movies.data.remote.MoviesRemoteApiService
import com.maxidev.movips.search.data.remote.SearchRemoteApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiServiceModule {

    @Provides
    @Singleton
    fun providesMoviesApiService(retrofit: Retrofit): MoviesRemoteApiService =
        retrofit.create(MoviesRemoteApiService::class.java)

    @Provides
    @Singleton
    fun providesDetailsApiService(retrofit: Retrofit): DetailsRemoteApiService =
        retrofit.create(DetailsRemoteApiService::class.java)

    @Provides
    @Singleton
    fun providesSearchApiService(retrofit: Retrofit): SearchRemoteApiService =
        retrofit.create(SearchRemoteApiService::class.java)
}