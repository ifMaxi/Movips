package com.maxidev.movips.movies.domain.models

data class TopRatedMovies(
    val id: Int,
    val title: String,
    val posterPath: String,
    val voteAverage: Double
)