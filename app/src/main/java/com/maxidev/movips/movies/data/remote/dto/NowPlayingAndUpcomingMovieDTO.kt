package com.maxidev.movips.movies.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NowPlayingAndUpcomingMovieDTO(
    val dates: Dates? = Dates(),
    val page: Int? = 0,
    val results: List<Result>? = listOf(),
    @SerialName("total_pages")
    val totalPages: Int? = 0,
    @SerialName("total_results")
    val totalResults: Int? = 0
)