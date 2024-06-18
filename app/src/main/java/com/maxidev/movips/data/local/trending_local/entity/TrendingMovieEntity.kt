package com.maxidev.movips.data.local.trending_local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trending_movie_entity")
data class TrendingMovieEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val posterPath: String,
    val backdropPath: String,
    val title: String,
    val voteAverage: Double
)