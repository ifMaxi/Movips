package com.maxidev.movips.domain.model

data class PopularMovies(
    val id: Int,
    val title: String,
    val posterPath: String,
    val popularity: Double
)