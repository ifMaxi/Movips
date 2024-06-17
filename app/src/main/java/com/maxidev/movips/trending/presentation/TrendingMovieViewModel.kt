package com.maxidev.movips.trending.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.maxidev.movips.trending.data.repositoy.TrendingMovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TrendingMovieViewModel @Inject constructor(
    private val repository: TrendingMovieRepository
): ViewModel() {

    val trendingFlow = repository.fetchTrendingMovies().cachedIn(viewModelScope)
}