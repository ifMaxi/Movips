package com.maxidev.movips.movies.data.local.dao.remote_key_dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.maxidev.movips.movies.data.local.entity.remote_key_entity.UpcomingMoviesRemoteKeyEntity

@Dao
interface UpcomingMoviesRemoteKeyDao {

    @Query("SELECT * FROM upcoming_movie_remote_key_table WHERE id =:id")
    suspend fun getRemoteKeys(id: Int): UpcomingMoviesRemoteKeyEntity

    @Upsert
    suspend fun addAllRemoteKeys(remoteKeys: List<UpcomingMoviesRemoteKeyEntity>)

    @Query("DELETE FROM upcoming_movie_remote_key_table")
    suspend fun deleteAllRemoteKeys()
}