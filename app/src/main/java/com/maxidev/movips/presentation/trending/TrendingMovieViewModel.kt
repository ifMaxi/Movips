package com.maxidev.movips.presentation.trending

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.maxidev.movips.data.repository.trending_repository.TrendingMovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TrendingMovieViewModel @Inject constructor(
    repository: TrendingMovieRepository
): ViewModel() {

    val trendingFlow = repository.fetchTrendingMovies().cachedIn(viewModelScope)
}