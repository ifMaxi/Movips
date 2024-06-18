package com.maxidev.movips.data.remote.trending_remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TrendingMovieDTO(
    val page: Int? = null,
    val results: List<Result?>? = null,
    @SerialName("total_pages")
    val totalPages: Int? = null,
    @SerialName("total_results")
    val totalResults: Int? = null
) {
    @Serializable
    data class Result(
        @SerialName("backdrop_path")
        val backdropPath: String? = null,
        val id: Int? = null,
        @SerialName("original_title")
        val originalTitle: String? = null,
        val overview: String? = null,
        @SerialName("poster_path")
        val posterPath: String? = null,
        @SerialName("media_type")
        val mediaType: String? = null,
        val adult: Boolean? = null,
        val title: String? = null,
        @SerialName("original_language")
        val originalLanguage: String? = null,
        @SerialName("genre_ids")
        val genreIds: List<Int?>? = null,
        val popularity: Double? = null,
        @SerialName("release_date")
        val releaseDate: String? = null,
        val video: Boolean? = null,
        @SerialName("vote_average")
        val voteAverage: Double? = null,
        @SerialName("vote_count")
        val voteCount: Int? = null
    )
}