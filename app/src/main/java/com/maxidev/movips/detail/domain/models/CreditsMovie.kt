package com.maxidev.movips.detail.domain.models

data class CreditsMovie(
    val id: Int,
    val knownForDepartment: String? = "",
    val name: String? = "",
    val character: String? = "",
    val profilePath: String? = ""
)