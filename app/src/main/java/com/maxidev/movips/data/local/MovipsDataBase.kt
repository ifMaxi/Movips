package com.maxidev.movips.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.maxidev.movips.data.local.movies_local.dao.NowPlayingMoviesDao
import com.maxidev.movips.data.local.movies_local.dao.PopularMoviesDao
import com.maxidev.movips.data.local.movies_local.dao.TopRatedMoviesDao
import com.maxidev.movips.data.local.movies_local.dao.UpcomingMoviesDao
import com.maxidev.movips.data.local.movies_local.dao.remote_key_dao.NowPlayingMoviesRemoteKeyDao
import com.maxidev.movips.data.local.movies_local.dao.remote_key_dao.PopularMoviesRemoteKeyDao
import com.maxidev.movips.data.local.movies_local.dao.remote_key_dao.TopRatedMoviesRemoteKeyDao
import com.maxidev.movips.data.local.movies_local.dao.remote_key_dao.UpcomingMoviesRemoteKeyDao
import com.maxidev.movips.data.local.movies_local.entity.NowPlayingMoviesEntity
import com.maxidev.movips.data.local.movies_local.entity.PopularMoviesEntity
import com.maxidev.movips.data.local.movies_local.entity.TopRatedMoviesEntity
import com.maxidev.movips.data.local.movies_local.entity.UpcomingMoviesEntity
import com.maxidev.movips.data.local.movies_local.entity.remote_key_entity.NowPlayingMoviesRemoteKeyEntity
import com.maxidev.movips.data.local.movies_local.entity.remote_key_entity.PopularMoviesRemoteKeyEntity
import com.maxidev.movips.data.local.movies_local.entity.remote_key_entity.TopRatedMoviesRemoteKeyEntity
import com.maxidev.movips.data.local.movies_local.entity.remote_key_entity.UpcomingMoviesRemoteKeyEntity
import com.maxidev.movips.data.local.trending_local.dao.TrendingMovieDao
import com.maxidev.movips.data.local.trending_local.dao.remote_key_dao.TrendingMoviesRemoteKeyDao
import com.maxidev.movips.data.local.trending_local.entity.TrendingMovieEntity
import com.maxidev.movips.data.local.trending_local.entity.remote_key_entity.TrendingMovieRemoteKeyEntity

@Database(
    entities = [
        NowPlayingMoviesEntity::class,
        PopularMoviesEntity::class,
        TopRatedMoviesEntity::class,
        UpcomingMoviesEntity::class,
        TrendingMovieEntity::class,
        NowPlayingMoviesRemoteKeyEntity::class,
        PopularMoviesRemoteKeyEntity::class,
        TopRatedMoviesRemoteKeyEntity::class,
        UpcomingMoviesRemoteKeyEntity::class,
        TrendingMovieRemoteKeyEntity::class
               ],
    version = 11,
    exportSchema = false
)
abstract class MovipsDataBase: RoomDatabase() {

    abstract fun nowPlayingMoviesDao(): NowPlayingMoviesDao
    abstract fun nowPlayingMoviesRemoteKeyDao(): NowPlayingMoviesRemoteKeyDao

    abstract fun popularMoviesDao(): PopularMoviesDao
    abstract fun popularMoviesRemoteKeyDao(): PopularMoviesRemoteKeyDao

    abstract fun topRatedMoviesDao(): TopRatedMoviesDao
    abstract fun topRatedMoviesRemoteKeyDao(): TopRatedMoviesRemoteKeyDao

    abstract fun upcomingMoviesDao(): UpcomingMoviesDao
    abstract fun upcomingMoviesRemoteKeyDao(): UpcomingMoviesRemoteKeyDao

    abstract fun trendingMovieDao(): TrendingMovieDao
    abstract fun trendingMovieRemoteKeyDao(): TrendingMoviesRemoteKeyDao
}