package com.maxidev.movips.data.repository.datasource

import com.maxidev.movips.data.remote.RemoteApiService
import com.maxidev.movips.domain.mappers.toExternalModel
import com.maxidev.movips.domain.model.DetailedMovie
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DetailedMovieDataSource @Inject constructor(
    private val api: RemoteApiService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun fetchDetail(movieId: Int): DetailedMovie =
        withContext(ioDispatcher) {
            api.getDetailedMovie(movieId).toExternalModel()
        }
}