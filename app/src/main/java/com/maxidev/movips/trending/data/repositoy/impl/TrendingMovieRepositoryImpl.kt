package com.maxidev.movips.trending.data.repositoy.impl

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.maxidev.movips.core.data.local.MovipsDataBase
import com.maxidev.movips.trending.data.paging.TrendingMovieRemoteMediator
import com.maxidev.movips.trending.data.remote.TrendingMovieRemoteApiService
import com.maxidev.movips.trending.data.repositoy.TrendingMovieRepository
import com.maxidev.movips.trending.domain.mappers.toExternalModel
import com.maxidev.movips.trending.domain.models.TrendingMovie
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