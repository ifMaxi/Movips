package com.maxidev.movips.di

import com.maxidev.movips.data.repository.detail_repository.DetailedMovieRepository
import com.maxidev.movips.data.repository.detail_repository.impl.DetailedMovieRepositoryImpl
import com.maxidev.movips.data.repository.movies_repository.MoviesRepository
import com.maxidev.movips.data.repository.movies_repository.impl.MoviesRepositoryImpl
import com.maxidev.movips.data.repository.search_repository.SearchMovieRepository
import com.maxidev.movips.data.repository.search_repository.impl.SearchMovieRepositoryImpl
import com.maxidev.movips.data.repository.trending_repository.TrendingMovieRepository
import com.maxidev.movips.data.repository.trending_repository.impl.TrendingMovieRepositoryImpl
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

    @Binds
    abstract fun bindsSearchMovieRepository(
        repositoryImpl: SearchMovieRepositoryImpl
    ): SearchMovieRepository

    @Binds
    abstract fun bindsTrendingMovieRepository(
        repositoryImpl: TrendingMovieRepositoryImpl
    ): TrendingMovieRepository
}