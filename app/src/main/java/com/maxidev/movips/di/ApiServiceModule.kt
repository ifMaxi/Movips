package com.maxidev.movips.di

import com.maxidev.movips.data.remote.detail_remote.DetailsRemoteApiService
import com.maxidev.movips.data.remote.movies_remote.MoviesRemoteApiService
import com.maxidev.movips.data.remote.search_remote.SearchRemoteApiService
import com.maxidev.movips.data.remote.trending_remote.TrendingMovieRemoteApiService
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

    @Provides
    @Singleton
    fun providesTrendingApiService(retrofit: Retrofit): TrendingMovieRemoteApiService =
        retrofit.create(TrendingMovieRemoteApiService::class.java)
}