package com.maxidev.movips.detail.data.repository.impl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.maxidev.movips.detail.data.datasource.CreditMovieDataSource
import com.maxidev.movips.detail.data.datasource.DetailedMovieDataSource
import com.maxidev.movips.detail.data.datasource.ImageMovieDataSource
import com.maxidev.movips.detail.data.paging.RecommendationsPagingSource
import com.maxidev.movips.detail.data.remote.DetailsRemoteApiService
import com.maxidev.movips.detail.data.repository.DetailedMovieRepository
import com.maxidev.movips.detail.domain.models.CreditsMovie
import com.maxidev.movips.detail.domain.models.DetailedMovie
import com.maxidev.movips.detail.domain.models.ImageMovie
import com.maxidev.movips.detail.domain.models.RecommendationsMovies
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DetailedMovieRepositoryImpl @Inject constructor(
    private val dataSource: DetailedMovieDataSource,
    private val creditsDataSource: CreditMovieDataSource,
    private val imageDataSource: ImageMovieDataSource,
    private val api: DetailsRemoteApiService
): DetailedMovieRepository {

    override suspend fun fetchedDetails(movieId: Int): DetailedMovie =
        dataSource.fetchDetail(movieId)

    override suspend fun fetchedCredits(movieId: Int): List<CreditsMovie> =
        creditsDataSource.fetchCredits(movieId).filter {
            it.knownForDepartment == "Acting"
        }

    override suspend fun fetchedImages(movieId: Int): List<ImageMovie> =
        imageDataSource.fetchImage(movieId)

    override fun fetchedRecommendations(movieId: Int): Flow<PagingData<RecommendationsMovies>> =
        Pager(
            config = PagingConfig(
                pageSize = 5,
                maxSize = 20
            ),
            pagingSourceFactory = {
                RecommendationsPagingSource(api, movieId = movieId)
            }
        ).flow
}