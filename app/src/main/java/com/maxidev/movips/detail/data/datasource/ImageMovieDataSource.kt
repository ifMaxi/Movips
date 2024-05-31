package com.maxidev.movips.detail.data.datasource

import com.maxidev.movips.detail.data.remote.DetailsRemoteApiService
import com.maxidev.movips.detail.domain.mappers.toExternalModel
import com.maxidev.movips.detail.domain.models.ImageMovie
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ImageMovieDataSource @Inject constructor(
    private val api: DetailsRemoteApiService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun fetchImage(movieId: Int): List<ImageMovie> =
        withContext(ioDispatcher) {
            api.getImagesMovie(movieId).toExternalModel()?.toList() ?: emptyList()
        }
}