package com.maxidev.movips.detail.data.repository.impl

import com.maxidev.movips.detail.data.repository.DetailedMovieRepository
import com.maxidev.movips.detail.data.datasource.DetailedMovieDataSource
import com.maxidev.movips.detail.domain.models.DetailedMovie
import javax.inject.Inject

class DetailedMovieRepositoryImpl @Inject constructor(
    private val dataSource: DetailedMovieDataSource
): DetailedMovieRepository {

    override suspend fun fetchedDetails(movieId: Int): DetailedMovie =
        dataSource.fetchDetail(movieId)
}