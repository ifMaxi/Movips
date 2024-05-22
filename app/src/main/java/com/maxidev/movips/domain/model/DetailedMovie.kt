package com.maxidev.movips.domain.model

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
    val spokenLanguages: List<String>
)