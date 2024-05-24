package com.maxidev.movips.search.domain.models

data class SearchMovie(
    val id: Int,
    val title: String,
    val posterPath: String,
    val voteAverage: Double,
    val releaseDate: String
)