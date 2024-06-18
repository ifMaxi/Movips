package com.maxidev.movips.data.remote.detail_remote.dto

import com.maxidev.movips.data.remote.movies_remote.dto.Result
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecommendationsMovieDTO(
    val page: Int? = null,
    val results: List<Result?>? = null,
    @SerialName("total_pages")
    val totalPages: Int? = null,
    @SerialName("total_results")
    val totalResults: Int? = null
)