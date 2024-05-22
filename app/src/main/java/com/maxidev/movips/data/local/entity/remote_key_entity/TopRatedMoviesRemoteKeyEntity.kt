package com.maxidev.movips.data.local.entity.remote_key_entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "top_rated_movie_remote_key_table")
data class TopRatedMoviesRemoteKeyEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val nextKey: Int?,
    val prevKey: Int?
)