package com.maxidev.movips.data.local.movies_local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "upcoming_movies_entity")
data class UpcomingMoviesEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String,
    val posterPath: String,
    val voteAverage: Double
)