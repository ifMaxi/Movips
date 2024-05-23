package com.maxidev.movips.movies.domain.models

data class PopularMovies(
    val id: Int,
    val title: String,
    val posterPath: String,
    val popularity: Double
)