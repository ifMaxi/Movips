package com.maxidev.movips.data.repository.trending_repository.impl

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.maxidev.movips.data.local.MovipsDataBase
import com.maxidev.movips.data.paging.trending_paging.TrendingMovieRemoteMediator
import com.maxidev.movips.data.remote.trending_remote.TrendingMovieRemoteApiService
import com.maxidev.movips.data.repository.trending_repository.TrendingMovieRepository
import com.maxidev.movips.domain.trending_domain.mappers.toExternalModel
import com.maxidev.movips.domain.trending_domain.models.TrendingMovie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class TrendingMovieRepositoryImpl @Inject constructor(
    private val api: TrendingMovieRemoteApiService,
    private val dataBase: MovipsDataBase
): TrendingMovieRepository {

    override fun fetchTrendingMovies(): Flow<PagingData<TrendingMovie>> {
        val pagingSourceFactory = {
            dataBase.trendingMovieDao().allPagingSource()
        }

        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = true
            ),
            remoteMediator = TrendingMovieRemoteMediator(
                database = dataBase,
                networkService = api
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
            .map { pagingData ->
                pagingData.map { it.toExternalModel() }
            }
    }
}