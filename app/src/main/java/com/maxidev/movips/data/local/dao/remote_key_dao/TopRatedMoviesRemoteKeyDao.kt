package com.maxidev.movips.data.local.dao.remote_key_dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.maxidev.movips.data.local.entity.remote_key_entity.TopRatedMoviesRemoteKeyEntity

@Dao
interface TopRatedMoviesRemoteKeyDao {

    @Query("SELECT * FROM top_rated_movie_remote_key_table WHERE id =:id")
    suspend fun getRemoteKeys(id: Int): TopRatedMoviesRemoteKeyEntity

    @Upsert
    suspend fun addAllRemoteKeys(remoteKeys: List<TopRatedMoviesRemoteKeyEntity>)

    @Query("DELETE FROM top_rated_movie_remote_key_table")
    suspend fun deleteAllRemoteKeys()
}