package com.maxidev.movips.detail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxidev.movips.detail.data.repository.DetailedMovieRepository
import com.maxidev.movips.detail.presentation.state.CreditsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class CreditsMovieViewModel @Inject constructor(
    private val repository: DetailedMovieRepository
): ViewModel() {

    private val _creditsState: MutableStateFlow<CreditsState> =
        MutableStateFlow(CreditsState.Success(emptyList()))
    val creditsState = _creditsState.asStateFlow()

    fun fetchedCredits(movieId: Int) = viewModelScope.launch {
        _creditsState.value = try {
            CreditsState.Success(repository.fetchedCredits(movieId))
        } catch (e: HttpException) {
            CreditsState.Error(e)
        } catch (e: IOException) {
            CreditsState.Error(e)
        }
    }
}