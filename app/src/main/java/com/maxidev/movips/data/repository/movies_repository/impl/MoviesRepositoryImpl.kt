package com.maxidev.movips.data.repository.movies_repository.impl

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.maxidev.movips.data.local.MovipsDataBase
import com.maxidev.movips.data.paging.movies_paging.NowPlayingMoviesRemoteMediator
import com.maxidev.movips.data.paging.movies_paging.PopularMoviesRemoteMediator
import com.maxidev.movips.data.paging.movies_paging.TopRatedMoviesRemoteMediator
import com.maxidev.movips.data.paging.movies_paging.UpcomingMoviesRemoteMediator
import com.maxidev.movips.data.remote.movies_remote.MoviesRemoteApiService
import com.maxidev.movips.data.repository.movies_repository.MoviesRepository
import com.maxidev.movips.domain.movies_domain.mappers.toExternalModel
import com.maxidev.movips.domain.movies_domain.models.Movies
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class MoviesRepositoryImpl @Inject constructor(
    private val api: MoviesRemoteApiService,
    private val db: MovipsDataBase
): MoviesRepository {

    override fun fetchNowPlayingMovies(): Flow<PagingData<Movies>> {
        val pagingSourceFactory =  {
            db.nowPlayingMoviesDao().allPagingSource()
        }

        return Pager(
            config = PagingConfig(
                pageSize = 40,
                prefetchDistance = 40 * 2
            ),
            remoteMediator = NowPlayingMoviesRemoteMediator(
                database = db,
                networkService = api
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
            .map { pagingData ->
                pagingData.map { it.toExternalModel() }
            }
    }

    override fun fetchTopRatedMovies(): Flow<PagingData<Movies>> {
        val pagingSourceFactory =  {
            db.topRatedMoviesDao().allPagingSource()
        }

        return Pager(
            config = PagingConfig(
                pageSize = 40,
                prefetchDistance = 40 * 2
            ),
            remoteMediator = TopRatedMoviesRemoteMediator(
                dataBase = db,
                networkService = api
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
            .map { pagingData ->
                pagingData.map { it.toExternalModel() }
            }
    }

    override fun fetchPopularMovies(): Flow<PagingData<Movies>> {
        val pagingSourceFactory =  {
            db.popularMoviesDao().allPagingSource()
        }

        return Pager(
            config = PagingConfig(
                pageSize = 40,
                prefetchDistance = 40 * 2
            ),
            remoteMediator = PopularMoviesRemoteMediator(
                dataBase = db,
                networkService = api
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
            .map { pagingData ->
                pagingData.map { it.toExternalModel() }
            }
    }

    override fun fetchUpcomingMovies(): Flow<PagingData<Movies>> {
        val pagingSourceFactory =  {
            db.upcomingMoviesDao().allPagingSource()
        }

        return Pager(
            config = PagingConfig(
                pageSize = 40,
                prefetchDistance = 40 * 2
            ),
            remoteMediator = UpcomingMoviesRemoteMediator(
                dataBase = db,
                networkService = api
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
            .map { pagingData ->
                pagingData.map { it.toExternalModel() }
            }
    }
}