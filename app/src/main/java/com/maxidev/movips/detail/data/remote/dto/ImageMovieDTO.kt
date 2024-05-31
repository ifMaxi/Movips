package com.maxidev.movips.detail.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageMovieDTO(
    val backdrops: List<Backdrop?>? = null,
    val id: Int? = null,
    val logos: List<Logo?>? = null,
    val posters: List<Poster?>? = null
) {
    @Serializable
    data class Backdrop(
        @SerialName("aspect_ratio")
        val aspectRatio: Double? = null,
        val height: Int? = null,
        @SerialName("iso_639_1")
        val iso6391: String? = null,
        @SerialName("file_path")
        val filePath: String? = null,
        @SerialName("vote_average")
        val voteAverage: Double? = null,
        @SerialName("vote_count")
        val voteCount: Int? = null,
        val width: Int? = null
    )

    @Serializable
    data class Logo(
        @SerialName("aspect_ratio")
        val aspectRatio: Double? = null,
        val height: Int? = null,
        @SerialName("iso_639_1")
        val iso6391: String? = null,
        @SerialName("file_path")
        val filePath: String? = null,
        @SerialName("vote_average")
        val voteAverage: Double? = null,
        @SerialName("vote_count")
        val voteCount: Int? = null,
        val width: Int? = null
    )

    @Serializable
    data class Poster(
        @SerialName("aspect_ratio")
        val aspectRatio: Double? = null,
        val height: Int? = null,
        @SerialName("iso_639_1")
        val iso6391: String? = null,
        @SerialName("file_path")
        val filePath: String? = null,
        @SerialName("vote_average")
        val voteAverage: Double? = null,
        @SerialName("vote_count")
        val voteCount: Int? = null,
        val width: Int? = null
    )
}