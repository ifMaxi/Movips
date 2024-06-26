package com.maxidev.movips.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.maxidev.movips.data.repository.detail_repository.DetailedMovieRepository
import com.maxidev.movips.presentation.detail.state.DetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class DetailedMovieViewModel @Inject constructor(
    private val repository: DetailedMovieRepository
): ViewModel() {

    private val _detailState: MutableStateFlow<DetailState> =
        MutableStateFlow(DetailState.Loading)
    val detailState = _detailState.asStateFlow()

    fun fetchedDetails(movieId: Int) = viewModelScope.launch {
        repository.fetchedDetails(movieId)
        _detailState.value = DetailState.Loading
        delay(1500)
        _detailState.value = try {
            DetailState.Success(onSuccess = repository.fetchedDetails(movieId))
        } catch (e: HttpException) {
            DetailState.Error(onError = e)
        } catch (e: IOException) {
            DetailState.Error(onError = e)
        }
    }

    fun pagerCredits(movieId: Int) = repository.fetchedCredits(movieId)
        .cachedIn(viewModelScope)

    fun pagerRecommendations(movieId: Int) = repository.fetchedRecommendations(movieId)
        .cachedIn(viewModelScope)
}