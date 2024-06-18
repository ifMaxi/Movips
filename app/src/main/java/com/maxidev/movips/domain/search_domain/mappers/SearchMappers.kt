package com.maxidev.movips.domain.search_domain.mappers

import com.maxidev.movips.data.remote.search_remote.dto.SearchMovieDTO
import com.maxidev.movips.domain.search_domain.models.SearchMovie

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