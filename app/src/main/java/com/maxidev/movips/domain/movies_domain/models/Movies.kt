package com.maxidev.movips.domain.movies_domain.models

data class Movies(
    val id: Int = 0,
    val title: String? = "",
    val posterPath: String? = "",
    val backdropPath: String? = "",
    val popularity: Double? = 0.0,
    val voteAverage: Double? = 0.0,
    val releaseDate: String? = ""
)