package com.maxidev.movips.presentation.detail

import com.maxidev.movips.domain.model.DetailedMovie

sealed interface DetailState {
    data class Success(val onSuccess: DetailedMovie): DetailState
    data class Error(val onError: Exception): DetailState
    data object Loading: DetailState
}