package com.maxidev.movips.domain.trending_domain.mappers

import com.maxidev.movips.data.local.trending_local.entity.TrendingMovieEntity
import com.maxidev.movips.data.remote.trending_remote.dto.TrendingMovieDTO
import com.maxidev.movips.domain.trending_domain.models.TrendingMovie

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