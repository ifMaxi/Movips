package com.maxidev.movips.domain.search_domain.models

data class SearchMovie(
    val id: Int,
    val title: String,
    val posterPath: String,
    val voteAverage: Double,
    val releaseDate: String
)