package com.maxidev.movips.detail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxidev.movips.detail.data.repository.DetailedMovieRepository
import com.maxidev.movips.detail.presentation.state.DetailState
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

    val pagerRecommendations = viewModelScope.launch {
        repository.fetchedRecommendations()
    }
}