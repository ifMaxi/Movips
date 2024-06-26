package com.maxidev.movips.presentation.search

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.maxidev.movips.data.repository.search_repository.SearchMovieRepository
import com.maxidev.movips.domain.search_domain.models.SearchMovie
import com.maxidev.movips.presentation.search.state.SearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchMovieViewModel @Inject constructor(
    private val repository: SearchMovieRepository
): ViewModel() {

    private val _searchQuery: MutableState<SearchState> = mutableStateOf(SearchState())
    val searchQuery: State<SearchState> = _searchQuery

    fun onSearchQueryChange(newQuery: String) {
        _searchQuery.value.query.value = newQuery
    }

    private val _searchedMovies = MutableStateFlow<PagingData<SearchMovie>>(PagingData.empty())
    val searchedMovies = _searchedMovies

    fun flowSearch(query: String) = viewModelScope.launch {
        repository.fetchSearchedMovies(
            query = query
        ).cachedIn(viewModelScope)
            .collect {
                _searchedMovies.value = it
            }
    }
}