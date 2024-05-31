package com.maxidev.movips.detail.presentation.state

import com.maxidev.movips.detail.domain.models.CreditsMovie

sealed interface CreditsState {
    data class Success(val onSuccess: List<CreditsMovie>): CreditsState
    data class Error(val onError: Exception): CreditsState
}