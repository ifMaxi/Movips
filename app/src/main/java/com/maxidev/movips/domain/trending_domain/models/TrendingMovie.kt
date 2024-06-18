package com.maxidev.movips.domain.trending_domain.models

data class TrendingMovie(
    val id: Int,
    val posterPath: String?,
    val backdropPath: String?,
    val title: String?,
    val voteAverage: Double?
)