package com.maxidev.movips.detail.data.datasource

import com.maxidev.movips.detail.data.remote.DetailsRemoteApiService
import com.maxidev.movips.detail.domain.mappers.toExternalModel
import com.maxidev.movips.detail.domain.models.CreditsMovie
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CreditMovieDataSource @Inject constructor(
    private val api: DetailsRemoteApiService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun fetchCredits(movieId: Int): List<CreditsMovie> =
        withContext(ioDispatcher) {
            api.getCreditsMovie(movieId = movieId).toExternalModel()?.toList() ?: emptyList()
        }
}