package com.maxidev.movips.data.local.trending_local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.maxidev.movips.data.local.trending_local.entity.TrendingMovieEntity

@Dao
interface TrendingMovieDao {

    @Upsert
    suspend fun insertAll(movies: List<TrendingMovieEntity>)

    @Query("SELECT * FROM trending_movie_entity")
    fun allPagingSource(): PagingSource<Int, TrendingMovieEntity>

    @Query("DELETE FROM trending_movie_entity")
    suspend fun clearAll()
}