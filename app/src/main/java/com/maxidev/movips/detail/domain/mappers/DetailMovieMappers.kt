package com.maxidev.movips.detail.domain.mappers

import com.maxidev.movips.detail.data.remote.dto.DetailedMovieDTO
import com.maxidev.movips.detail.domain.models.DetailedMovie

/**
 * Mappers for DetailedMovie
 */

fun DetailedMovieDTO.toExternalModel() =
    DetailedMovie(
        id = this.id ?: 0,
        homePage = this.homepage.toString(),
        title = this.title.toString(),
        overview = this.overview.toString(),
        posterPath = this.posterPath.toString(),
        backdropPath = this.backdropPath.toString(),
        productionCountry = this.productionCountries?.map { it?.name.toString() } ?: emptyList(),
        releaseDate = this.releaseDate.toString(),
        releaseStatus = this.status.toString(),
        genres = this.genres?.map { it?.name.toString() } ?: emptyList(),
        tagline = this.tagline.toString(),
        spokenLanguages = this.spokenLanguages?.map { it?.name.toString() } ?: emptyList(),
        productionCompanies = this.productionCompanies?.map { it?.name.toString() } ?: emptyList(),
    )