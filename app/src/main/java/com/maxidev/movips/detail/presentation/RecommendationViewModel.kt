package com.maxidev.movips.detail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.maxidev.movips.detail.data.repository.DetailedMovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecommendationViewModel @Inject constructor(
    private val repository: DetailedMovieRepository
): ViewModel() {

    fun pagerRecommendations(movieId: Int) = repository.fetchedRecommendations(movieId)
        .cachedIn(viewModelScope)
}