package com.maxidev.movips.detail.domain.models

data class DetailedMovie(
    val id: Int,
    val homePage: String,
    val title: String,
    val overview: String,
    val posterPath: String,
    val backdropPath: String,
    val productionCompanies: List<String>,
    val productionCountry: List<String>,
    val releaseDate: String,
    val releaseStatus: String,
    val genres: List<String>,
    val tagline: String,
    val spokenLanguages: List<String>,
    val budget: Int,
    val revenue: Int,
    val voteAverage: Double
)