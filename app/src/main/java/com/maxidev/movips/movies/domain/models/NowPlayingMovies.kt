package com.maxidev.movips.movies.domain.models

data class NowPlayingMovies(
    val id: Int,
    val title: String,
    val posterPath: String,
    val backdropPath: String
)