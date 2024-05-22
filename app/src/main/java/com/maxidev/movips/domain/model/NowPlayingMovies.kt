package com.maxidev.movips.domain.model

data class NowPlayingMovies(
    val id: Int,
    val title: String,
    val posterPath: String,
    val backdropPath: String
)