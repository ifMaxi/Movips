package com.maxidev.movips.core.di

import com.maxidev.movips.core.data.local.MovipsDataBase
import com.maxidev.movips.movies.data.local.dao.NowPlayingMoviesDao
import com.maxidev.movips.movies.data.local.dao.PopularMoviesDao
import com.maxidev.movips.movies.data.local.dao.TopRatedMoviesDao
import com.maxidev.movips.movies.data.local.dao.UpcomingMoviesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Provides
    @Singleton
    fun providesNowPlayingMoviesDao(database: MovipsDataBase): NowPlayingMoviesDao =
        database.nowPlayingMoviesDao()

    @Provides
    @Singleton
    fun providesPopularMoviesDao(database: MovipsDataBase): PopularMoviesDao =
        database.popularMoviesDao()

    @Provides
    @Singleton
    fun providesTopRatedMoviesDao(database: MovipsDataBase): TopRatedMoviesDao =
        database.topRatedMoviesDao()

    @Provides
    @Singleton
    fun providesUpcomingMoviesDao(database: MovipsDataBase): UpcomingMoviesDao =
        database.upcomingMoviesDao()
}