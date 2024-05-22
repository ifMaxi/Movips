package com.maxidev.movips.data.local.entity.remote_key_entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "now_playing_movie_remote_key_table")
data class NowPlayingMoviesRemoteKeyEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val nextKey: Int?,
    val prevKey: Int?
)