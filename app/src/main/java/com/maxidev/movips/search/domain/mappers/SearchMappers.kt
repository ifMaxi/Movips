package com.maxidev.movips.search.domain.mappers

import com.maxidev.movips.search.data.remote.dto.SearchMovieDTO
import com.maxidev.movips.search.domain.models.SearchMovie

/**
 * Mappers for SearchMovie
 */

fun SearchMovieDTO.toMovieExternalModel() =
    this.results?.map {
        SearchMovie(
            id = it?.id ?: 0,
            title = it?.title ?: "",
            posterPath = it?.posterPath ?: "",
            voteAverage = it?.voteAverage ?: 0.0,
            releaseDate = it?.releaseDate ?: ""
        )
    }