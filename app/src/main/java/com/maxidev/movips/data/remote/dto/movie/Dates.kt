package com.maxidev.movips.data.remote.dto.movie

import kotlinx.serialization.Serializable

@Serializable
data class Dates(
    val maximum: String? = null,
    val minimum: String? = null
)