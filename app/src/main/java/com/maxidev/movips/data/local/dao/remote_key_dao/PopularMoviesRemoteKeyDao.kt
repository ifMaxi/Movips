package com.maxidev.movips.data.local.dao.remote_key_dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.maxidev.movips.data.local.entity.remote_key_entity.PopularMoviesRemoteKeyEntity

@Dao
interface PopularMoviesRemoteKeyDao {

    @Query("SELECT * FROM popular_movie_remote_key_table WHERE id =:id")
    suspend fun getRemoteKeys(id: Int): PopularMoviesRemoteKeyEntity

    @Upsert
    suspend fun addAllRemoteKeys(remoteKeys: List<PopularMoviesRemoteKeyEntity>)

    @Query("DELETE FROM popular_movie_remote_key_table")
    suspend fun deleteAllRemoteKeys()
}