package com.maxidev.movips.domain.mappers

import com.maxidev.movips.data.local.entity.NowPlayingMoviesEntity
import com.maxidev.movips.data.local.entity.PopularMoviesEntity
import com.maxidev.movips.data.local.entity.TopRatedMoviesEntity
import com.maxidev.movips.data.local.entity.UpcomingMoviesEntity
import com.maxidev.movips.data.remote.dto.details.DetailedMovieDTO
import com.maxidev.movips.data.remote.dto.movie.NowPlayingAndUpcomingMovieDTO
import com.maxidev.movips.data.remote.dto.movie.PopularAndTopRatedMoviesDTO
import com.maxidev.movips.domain.model.DetailedMovie
import com.maxidev.movips.domain.model.NowPlayingMovies
import com.maxidev.movips.domain.model.PopularMovies
import com.maxidev.movips.domain.model.TopRatedMovies
import com.maxidev.movips.domain.model.UpcomingMovies

/**
 * Mappers for NowPlayingMovies.
 */

fun NowPlayingAndUpcomingMovieDTO.toNowPlayingEntity() =
    this.results?.map { data ->
        NowPlayingMoviesEntity(
            id = data.id ?: 0,
            title = data.title.toString(),
            posterPath = data.posterPath.toString(),
            backdropPath = data.backdropPath.toString()
        )
    }

fun NowPlayingMoviesEntity.toNowPlayingExternalModel() =
    NowPlayingMovies(
        id = id,
        title = title,
        posterPath = posterPath,
        backdropPath = backdropPath
    )

/**
 * Mappers for TopRatedMovies
 */

fun PopularAndTopRatedMoviesDTO.toTopRatedEntity() =
    this.results?.map { data ->
        TopRatedMoviesEntity(
            id = data.id ?: 0,
            title = data.title.toString(),
            posterPath = data.posterPath.toString(),
            voteAverage = data.voteAverage ?: 0.0
        )
    }

fun TopRatedMoviesEntity.toTopRatedExternalModel() =
    TopRatedMovies(
        id = id,
        title = title,
        posterPath = posterPath,
        voteAverage = voteAverage
    )

/**
 * Mappers for PopularMovies
 */

fun PopularAndTopRatedMoviesDTO.toPopularEntity() =
    this.results?.map { data ->
        PopularMoviesEntity(
            id = data.id ?: 0,
            posterPath = data.posterPath.toString(),
            title = data.title.toString(),
            popularity = data.popularity ?: 0.0
        )
    }

fun PopularMoviesEntity.toPopularExternalModel() =
    PopularMovies(
        id = id,
        title = title,
        posterPath = posterPath,
        popularity = popularity
    )

/**
 * Mappers for UpcomingMovies
 */

fun NowPlayingAndUpcomingMovieDTO.toUpcomingEntity() =
    this.results?.map { data ->
        UpcomingMoviesEntity(
            id = data.id ?: 0,
            title = data.title.toString(),
            posterPath = data.posterPath.toString(),
            releaseDate = data.releaseDate.toString()
        )
    }

fun UpcomingMoviesEntity.toUpcomingExternalModel() =
    UpcomingMovies(
        id = id,
        title = title,
        posterPath = posterPath,
        releaseDate = releaseDate
    )

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