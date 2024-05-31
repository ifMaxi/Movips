package com.maxidev.movips.detail.presentation.state

import com.maxidev.movips.detail.domain.models.ImageMovie

sealed interface ImageState {
    data class Success(val onSuccess: List<ImageMovie>): ImageState
    data class Error(val onError: Exception): ImageState
}