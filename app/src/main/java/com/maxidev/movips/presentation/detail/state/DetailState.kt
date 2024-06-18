package com.maxidev.movips.presentation.detail.state

import com.maxidev.movips.domain.detail_domain.models.DetailedMovie

sealed interface DetailState {
    data class Success(val onSuccess: DetailedMovie): DetailState
    data class Error(val onError: Exception): DetailState
    data object Loading: DetailState
}