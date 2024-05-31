package com.maxidev.movips.detail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxidev.movips.detail.data.repository.DetailedMovieRepository
import com.maxidev.movips.detail.presentation.state.ImageState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class ImageBackViewModel @Inject constructor(
    private val repository: DetailedMovieRepository
): ViewModel() {

    private val _imageState: MutableStateFlow<ImageState> =
        MutableStateFlow(ImageState.Success(emptyList()))
    val imageState = _imageState.asStateFlow()

    fun fetchedImages(movieId: Int) = viewModelScope.launch {
        _imageState.value = try {
            ImageState.Success(repository.fetchedImages(movieId))
        } catch (e: HttpException) {
            ImageState.Error(e)
        } catch (e: IOException) {
            ImageState.Error(e)
        }
    }
}