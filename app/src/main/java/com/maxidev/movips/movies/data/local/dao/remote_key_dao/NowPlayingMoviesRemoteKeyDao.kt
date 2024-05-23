package com.maxidev.movips.movies.data.local.dao.remote_key_dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.maxidev.movips.movies.data.local.entity.remote_key_entity.NowPlayingMoviesRemoteKeyEntity

@Dao
interface NowPlayingMoviesRemoteKeyDao {

    @Query("SELECT * FROM now_playing_movie_remote_key_table WHERE id =:id")
    suspend fun getRemoteKeys(id: Int): NowPlayingMoviesRemoteKeyEntity

    @Upsert
    suspend fun addAllRemoteKeys(remoteKeys: List<NowPlayingMoviesRemoteKeyEntity>)

    @Query("DELETE FROM now_playing_movie_remote_key_table")
    suspend fun deleteAllRemoteKeys()
}