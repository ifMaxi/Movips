package com.maxidev.movips.movies.domain.mappers

import com.maxidev.movips.movies.data.local.entity.NowPlayingMoviesEntity
import com.maxidev.movips.movies.data.local.entity.PopularMoviesEntity
import com.maxidev.movips.movies.data.local.entity.TopRatedMoviesEntity
import com.maxidev.movips.movies.data.local.entity.UpcomingMoviesEntity
import com.maxidev.movips.movies.data.remote.dto.NowPlayingAndUpcomingMovieDTO
import com.maxidev.movips.movies.data.remote.dto.PopularAndTopRatedMoviesDTO
import com.maxidev.movips.movies.domain.models.Movies

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

fun NowPlayingMoviesEntity.toExternalModel() =
    Movies(
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

fun TopRatedMoviesEntity.toExternalModel() =
    Movies(
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
            voteAverage = data.voteAverage ?: 0.0
        )
    }

fun PopularMoviesEntity.toExternalModel() =
    Movies(
        id = id,
        title = title,
        posterPath = posterPath,
        voteAverage = voteAverage,
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
            voteAverage = data.voteAverage ?: 0.0
        )
    }

fun UpcomingMoviesEntity.toExternalModel() =
    Movies(
        id = id,
        title = title,
        posterPath = posterPath,
        voteAverage = voteAverage
    )