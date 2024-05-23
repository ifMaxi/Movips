package com.maxidev.movips.movies.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.maxidev.movips.movies.data.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val repository: MoviesRepository
): ViewModel() {

    val nowPlayFlow = repository.fetchNowPlayingMovies().cachedIn(viewModelScope)

    val popularFlow = repository.fetchPopularMovies().cachedIn(viewModelScope)

    val topRatedFlow = repository.fetchTopRatedMovies().cachedIn(viewModelScope)

    val upcomingFlow = repository.fetchUpcomingMovies().cachedIn(viewModelScope)
}