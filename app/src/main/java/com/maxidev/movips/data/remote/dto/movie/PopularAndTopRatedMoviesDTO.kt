package com.maxidev.movips.data.remote.dto.movie

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PopularAndTopRatedMoviesDTO(
    val page: Int? = 0,
    val results: List<Result>? = listOf(),
    @SerialName("total_pages")
    val totalPages: Int? = 0,
    @SerialName("total_results")
    val totalResults: Int? = 0
)