package com.maxidev.movips.detail.data.datasource

import com.maxidev.movips.detail.domain.mappers.toExternalModel
import com.maxidev.movips.movies.data.remote.MoviesRemoteApiService
import com.maxidev.movips.detail.domain.models.DetailedMovie
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DetailedMovieDataSource @Inject constructor(
    private val api: MoviesRemoteApiService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun fetchDetail(movieId: Int): DetailedMovie =
        withContext(ioDispatcher) {
            api.getDetailedMovie(movieId).toExternalModel()
        }
}