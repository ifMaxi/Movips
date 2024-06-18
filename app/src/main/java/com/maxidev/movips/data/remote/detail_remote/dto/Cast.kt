package com.maxidev.movips.data.remote.detail_remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Cast(
    val adult: Boolean? = null,
    val gender: Int? = null,
    val id: Int? = null,
    @SerialName("known_for_department")
    val knownForDepartment: String? = null,
    val name: String? = null,
    @SerialName("original_name")
    val originalName: String? = null,
    val popularity: Double? = null,
    @SerialName("profile_path")
    val profilePath: String? = null,
    @SerialName("cast_id")
    val castId: Int? = null,
    val character: String? = null,
    @SerialName("credit_id")
    val creditId: String? = null,
    val order: Int? = null
)