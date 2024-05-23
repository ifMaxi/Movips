package com.maxidev.movips.detail.presentation.state

import com.maxidev.movips.detail.domain.models.DetailedMovie

sealed interface DetailState {
    data class Success(val onSuccess: DetailedMovie): DetailState
    data class Error(val onError: Exception): DetailState
    data object Loading: DetailState
}