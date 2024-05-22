package com.maxidev.movips.domain.model

data class TopRatedMovies(
    val id: Int,
    val title: String,
    val posterPath: String,
    val voteAverage: Double
)