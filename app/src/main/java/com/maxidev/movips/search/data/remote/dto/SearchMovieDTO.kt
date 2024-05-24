package com.maxidev.movips.search.data.remote.dto

import com.maxidev.movips.movies.data.remote.dto.Result
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchMovieDTO(
    val page: Int? = null,
    val results: List<Result?>? = null,
    @SerialName("total_pages")
    val totalPages: Int? = null,
    @SerialName("total_results")
    val totalResults: Int? = null
)