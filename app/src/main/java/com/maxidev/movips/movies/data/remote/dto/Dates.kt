package com.maxidev.movips.movies.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class Dates(
    val maximum: String? = null,
    val minimum: String? = null
)