package com.maxidev.movips.detail.domain.mappers

import com.maxidev.movips.detail.data.remote.dto.CreditsMovieDTO
import com.maxidev.movips.detail.data.remote.dto.DetailedMovieDTO
import com.maxidev.movips.detail.data.remote.dto.ImageMovieDTO
import com.maxidev.movips.detail.data.remote.dto.RecommendationsMovieDTO
import com.maxidev.movips.detail.domain.models.CreditsMovie
import com.maxidev.movips.detail.domain.models.DetailedMovie
import com.maxidev.movips.detail.domain.models.ImageMovie
import com.maxidev.movips.detail.domain.models.RecommendationsMovies

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
 * Mappers fot ImageMovie
 */

fun ImageMovieDTO.toExternalModel() =
    this.backdrops?.map {
        ImageMovie(
            id = id ?: 0,
            filePath = it?.filePath.toString()
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