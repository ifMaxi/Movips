package com.maxidev.movips.data.local.movies_local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.maxidev.movips.data.local.movies_local.entity.TopRatedMoviesEntity

@Dao
interface TopRatedMoviesDao {

    @Upsert
    suspend fun insertAll(movies: List<TopRatedMoviesEntity>)

    @Query("SELECT * FROM top_rated_movies_entity")
    fun allPagingSource(): PagingSource<Int, TopRatedMoviesEntity>

    @Query("DELETE FROM top_rated_movies_entity")
    suspend fun clearAll()
}