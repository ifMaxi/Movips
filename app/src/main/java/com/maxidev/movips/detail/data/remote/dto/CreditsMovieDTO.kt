package com.maxidev.movips.detail.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class CreditsMovieDTO(
    val id: Int? = null,
    val cast: List<Cast?>? = null
)