package com.maxidev.movips.movies.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "popular_movies_entity")
data class PopularMoviesEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val posterPath: String,
    val title: String,
    val voteAverage: Double,
)