package com.maxidev.movips.movies.domain.models

data class UpcomingMovies(
    val id: Int,
    val title: String,
    val posterPath: String,
    val releaseDate: String
)