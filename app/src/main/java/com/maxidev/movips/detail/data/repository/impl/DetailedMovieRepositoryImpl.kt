package com.maxidev.movips.detail.data.repository.impl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.filter
import com.maxidev.movips.detail.data.datasource.CreditMovieDataSource
import com.maxidev.movips.detail.data.datasource.DetailedMovieDataSource
import com.maxidev.movips.detail.data.paging.CreditsPagingSource
import com.maxidev.movips.detail.data.paging.RecommendationsPagingSource
import com.maxidev.movips.detail.data.remote.DetailsRemoteApiService
import com.maxidev.movips.detail.data.repository.DetailedMovieRepository
import com.maxidev.movips.detail.domain.models.CreditsMovie
import com.maxidev.movips.detail.domain.models.DetailedMovie
import com.maxidev.movips.detail.domain.models.RecommendationsMovies
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DetailedMovieRepositoryImpl @Inject constructor(
    private val dataSource: DetailedMovieDataSource,
    private val creditsDataSource: CreditMovieDataSource,
    private val api: DetailsRemoteApiService
): DetailedMovieRepository {

    override suspend fun fetchedDetails(movieId: Int): DetailedMovie =
        dataSource.fetchDetail(movieId)

    override fun fetchedCredits(movieId: Int): Flow<PagingData<CreditsMovie>> =
        Pager(
            config = PagingConfig(
                pageSize = 100
            ),
            pagingSourceFactory = {
                CreditsPagingSource(api, movieId = movieId)
            }
        ).flow
            .map { it ->
                it.filter {
                    it.knownForDepartment == "Acting"
                }
            }
//        creditsDataSource.fetchCredits(movieId).filter {
//            it.knownForDepartment == "Acting"
//        }

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