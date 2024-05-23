package com.maxidev.movips.movies.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.maxidev.movips.movies.data.local.entity.UpcomingMoviesEntity

@Dao
interface UpcomingMoviesDao {

    @Upsert
    suspend fun insertAll(movies: List<UpcomingMoviesEntity>)

    @Query("SELECT * FROM upcoming_movies_entity ORDER BY releaseDate ASC")
    fun allPagingSource(): PagingSource<Int, UpcomingMoviesEntity>

    @Query("DELETE FROM upcoming_movies_entity")
    suspend fun clearAll()
}