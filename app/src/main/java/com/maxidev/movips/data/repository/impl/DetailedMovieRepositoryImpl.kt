package com.maxidev.movips.data.repository.impl

import com.maxidev.movips.data.repository.DetailedMovieRepository
import com.maxidev.movips.data.repository.datasource.DetailedMovieDataSource
import com.maxidev.movips.domain.model.DetailedMovie
import javax.inject.Inject

class DetailedMovieRepositoryImpl @Inject constructor(
    private val dataSource: DetailedMovieDataSource
): DetailedMovieRepository {

    override suspend fun fetchedDetails(movieId: Int): DetailedMovie =
        dataSource.fetchDetail(movieId)
}