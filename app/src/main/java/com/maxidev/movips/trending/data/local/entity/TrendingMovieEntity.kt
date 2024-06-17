package com.maxidev.movips.trending.data.local.entity

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