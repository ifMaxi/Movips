package com.maxidev.movips.data.remote.detail_remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class CreditsMovieDTO(
    val id: Int? = null,
    val cast: List<Cast?>? = null
)