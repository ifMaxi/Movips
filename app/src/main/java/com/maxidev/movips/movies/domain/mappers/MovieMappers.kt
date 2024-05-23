package com.maxidev.movips.movies.domain.mappers

import com.maxidev.movips.movies.data.local.entity.NowPlayingMoviesEntity
import com.maxidev.movips.movies.data.local.entity.PopularMoviesEntity
import com.maxidev.movips.movies.data.local.entity.TopRatedMoviesEntity
import com.maxidev.movips.movies.data.local.entity.UpcomingMoviesEntity
import com.maxidev.movips.movies.data.remote.dto.NowPlayingAndUpcomingMovieDTO
import com.maxidev.movips.movies.data.remote.dto.PopularAndTopRatedMoviesDTO
import com.maxidev.movips.movies.domain.models.NowPlayingMovies
import com.maxidev.movips.movies.domain.models.PopularMovies
import com.maxidev.movips.movies.domain.models.TopRatedMovies
import com.maxidev.movips.movies.domain.models.UpcomingMovies

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