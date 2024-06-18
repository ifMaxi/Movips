package com.maxidev.movips.data.datasource.detail_datasource

import com.maxidev.movips.data.remote.detail_remote.DetailsRemoteApiService
import com.maxidev.movips.domain.detail_domain.mappers.toExternalModel
import com.maxidev.movips.domain.detail_domain.models.DetailedMovie
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DetailedMovieDataSource @Inject constructor(
    private val api: DetailsRemoteApiService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun fetchDetail(movieId: Int): DetailedMovie =
        withContext(ioDispatcher) {
            api.getDetailedMovie(movieId).toExternalModel()
        }
}