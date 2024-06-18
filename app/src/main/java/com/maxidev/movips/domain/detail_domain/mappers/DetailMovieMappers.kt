package com.maxidev.movips.domain.detail_domain.mappers

import com.maxidev.movips.data.remote.detail_remote.dto.CreditsMovieDTO
import com.maxidev.movips.data.remote.detail_remote.dto.DetailedMovieDTO
import com.maxidev.movips.data.remote.detail_remote.dto.RecommendationsMovieDTO
import com.maxidev.movips.domain.detail_domain.models.CreditsMovie
import com.maxidev.movips.domain.detail_domain.models.DetailedMovie
import com.maxidev.movips.domain.detail_domain.models.RecommendationsMovies

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
        budget = this.budget ?: 0,
        revenue = this.revenue ?: 0,
        voteAverage = this.voteAverage ?: 0.0
    )

/**
 * Mappers for CreditsMovie
 */

fun CreditsMovieDTO.toExternalModel() =
    this.cast?.map {
        CreditsMovie(
            id = it?.id ?: 0,
            knownForDepartment = it?.knownForDepartment.toString(),
            name = it?.name.toString(),
            character = it?.character.toString(),
            profilePath = it?.profilePath.toString()
        )
    }

/**
 * Mappers for RecommendationMovie
 */

fun RecommendationsMovieDTO.toExternalModel() =
    this.results?.map {
        RecommendationsMovies(
            id = it?.id ?: 0,
            title = it?.title.toString(),
            posterPath = it?.posterPath.toString()
        )
    }