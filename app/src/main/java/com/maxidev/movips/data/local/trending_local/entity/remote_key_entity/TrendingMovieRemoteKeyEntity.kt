package com.maxidev.movips.data.local.trending_local.entity.remote_key_entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trending_movie_remote_key_table")
data class TrendingMovieRemoteKeyEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val nextKey: Int?,
    val prevKey: Int?
)