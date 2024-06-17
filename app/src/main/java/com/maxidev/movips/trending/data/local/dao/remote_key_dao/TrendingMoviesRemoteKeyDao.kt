package com.maxidev.movips.trending.data.local.dao.remote_key_dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.maxidev.movips.trending.data.local.entity.remote_key_entity.TrendingMovieRemoteKeyEntity

@Dao
interface TrendingMoviesRemoteKeyDao {

    @Query("SELECT * FROM trending_movie_remote_key_table WHERE id =:id")
    suspend fun getRemoteKeys(id: Int): TrendingMovieRemoteKeyEntity

    @Upsert
    suspend fun addAllRemoteKeys(remoteKeys: List<TrendingMovieRemoteKeyEntity>)

    @Query("DELETE FROM trending_movie_remote_key_table")
    suspend fun deleteAllRemoteKeys()
}