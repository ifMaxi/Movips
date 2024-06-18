package com.maxidev.movips.domain.detail_domain.models

data class CreditsMovie(
    val id: Int,
    val knownForDepartment: String? = "",
    val name: String? = "",
    val character: String? = "",
    val profilePath: String? = ""
)