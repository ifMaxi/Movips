package com.maxidev.movips.data.remote.movies_remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class Dates(
    val maximum: String? = null,
    val minimum: String? = null
)