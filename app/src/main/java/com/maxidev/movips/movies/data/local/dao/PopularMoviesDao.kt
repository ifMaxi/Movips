package com.maxidev.movips.movies.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.maxidev.movips.movies.data.local.entity.PopularMoviesEntity

@Dao
interface PopularMoviesDao {

    @Upsert
    suspend fun insertAll(movies: List<PopularMoviesEntity>)

    @Query("SELECT * FROM popular_movies_entity")
    fun allPagingSource(): PagingSource<Int, PopularMoviesEntity>

    @Query("DELETE FROM popular_movies_entity")
    suspend fun clearAll()
}