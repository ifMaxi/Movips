package com.maxidev.movips.trending.domain.models

data class TrendingMovie(
    val id: Int,
    val posterPath: String?,
    val backdropPath: String?,
    val title: String?,
    val voteAverage: Double?
)