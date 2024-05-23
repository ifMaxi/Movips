package com.maxidev.movips.movies.data.repository.impl

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.maxidev.movips.core.data.local.MovipsDataBase
import com.maxidev.movips.movies.data.remote.MoviesRemoteApiService
import com.maxidev.movips.movies.data.repository.MoviesRepository
import com.maxidev.movips.movies.data.paging.NowPlayingMoviesRemoteMediator
import com.maxidev.movips.movies.data.paging.PopularMoviesRemoteMediator
import com.maxidev.movips.movies.data.paging.TopRatedMoviesRemoteMediator
import com.maxidev.movips.movies.data.paging.UpcomingMoviesRemoteMediator
import com.maxidev.movips.movies.domain.mappers.toNowPlayingExternalModel
import com.maxidev.movips.movies.domain.mappers.toPopularExternalModel
import com.maxidev.movips.movies.domain.mappers.toTopRatedExternalModel
import com.maxidev.movips.movies.domain.mappers.toUpcomingExternalModel
import com.maxidev.movips.movies.domain.models.NowPlayingMovies
import com.maxidev.movips.movies.domain.models.PopularMovies
import com.maxidev.movips.movies.domain.models.TopRatedMovies
import com.maxidev.movips.movies.domain.models.UpcomingMovies
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class MoviesRepositoryImpl @Inject constructor(
    private val api: MoviesRemoteApiService,
    private val db: MovipsDataBase
): MoviesRepository {

    override fun fetchNowPlayingMovies(): Flow<PagingData<NowPlayingMovies>> {
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
                pagingData.map { it.toNowPlayingExternalModel() }
            }
    }

    override fun fetchTopRatedMovies(): Flow<PagingData<TopRatedMovies>> {
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
                pagingData.map { it.toTopRatedExternalModel() }
            }
    }

    override fun fetchPopularMovies(): Flow<PagingData<PopularMovies>> {
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
                pagingData.map { it.toPopularExternalModel() }
            }
    }

    override fun fetchUpcomingMovies(): Flow<PagingData<UpcomingMovies>> {
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
                pagingData.map { it.toUpcomingExternalModel() }
            }
    }
}