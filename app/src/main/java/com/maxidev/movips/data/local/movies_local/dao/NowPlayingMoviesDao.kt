package com.maxidev.movips.data.local.movies_local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.maxidev.movips.data.local.movies_local.entity.NowPlayingMoviesEntity

@Dao
interface NowPlayingMoviesDao {

    @Upsert
    suspend fun insertAll(movies: List<NowPlayingMoviesEntity>)

    @Query("SELECT * FROM now_playing_movies_entity")
    fun allPagingSource(): PagingSource<Int, NowPlayingMoviesEntity>

    @Query("DELETE FROM now_playing_movies_entity")
    suspend fun clearAll()
}