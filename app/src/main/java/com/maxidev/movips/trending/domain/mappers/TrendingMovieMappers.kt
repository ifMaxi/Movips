package com.maxidev.movips.trending.domain.mappers

import com.maxidev.movips.trending.data.local.entity.TrendingMovieEntity
import com.maxidev.movips.trending.data.remote.dto.TrendingMovieDTO
import com.maxidev.movips.trending.domain.models.TrendingMovie

fun TrendingMovieDTO.toEntity() =
    this.results?.map {
        TrendingMovieEntity(
            id = it?.id ?: 0,
            posterPath = it?.posterPath ?: "",
            backdropPath = it?.backdropPath ?: "",
            title = it?.title ?: "",
            voteAverage = it?.voteAverage ?: 0.0
        )
    }

fun TrendingMovieEntity.toExternalModel() =
    TrendingMovie(
        id = id,
        posterPath = posterPath,
        backdropPath = backdropPath,
        title = title,
        voteAverage = voteAverage
    )