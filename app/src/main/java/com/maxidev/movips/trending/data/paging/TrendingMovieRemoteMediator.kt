package com.maxidev.movips.trending.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.maxidev.movips.core.data.local.MovipsDataBase
import com.maxidev.movips.trending.data.local.entity.TrendingMovieEntity
import com.maxidev.movips.trending.data.local.entity.remote_key_entity.TrendingMovieRemoteKeyEntity
import com.maxidev.movips.trending.data.remote.TrendingMovieRemoteApiService
import com.maxidev.movips.trending.domain.mappers.toEntity
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class TrendingMovieRemoteMediator(
    private val database: MovipsDataBase,
    private val networkService: TrendingMovieRemoteApiService,
): RemoteMediator<Int, TrendingMovieEntity>() {

    private val trendingDao = database.trendingMovieDao()
    private val remoteKeyDao = database.trendingMovieRemoteKeyDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, TrendingMovieEntity>
    ): MediatorResult {
        val loadKey = when(loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: 1
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevPage = remoteKeys?.prevKey
                    ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                prevPage
            }
            LoadType.APPEND -> {
                if (state.config.prefetchDistance == 0) {
                    return MediatorResult.Success(
                        endOfPaginationReached = true
                    )
                }

                val remoteKey = getRemoteKeyForLastItem(state)
                val nextPage = remoteKey?.nextKey
                    ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKey != null
                    )
                nextPage
            }
        }

        return try {
            val response = networkService.getTrendingMovie(page = loadKey)

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    trendingDao.clearAll()
                    remoteKeyDao.deleteAllRemoteKeys()
                }

                val prevPage = when (response.page) {
                    1 -> null
                    else -> response.page?.minus(1)
                }
                val nextPage = when (response.page) {
                    response.totalPages -> null
                    else -> response.page?.plus(1)
                }

                val keys = response.results?.map { data ->
                    TrendingMovieRemoteKeyEntity(
                        id = data?.id ?: 0,
                        nextKey = nextPage,
                        prevKey = prevPage
                    )
                }
                remoteKeyDao.addAllRemoteKeys(keys!!)
                response.toEntity()?.let { trendingDao.insertAll(it) }
            }
            MediatorResult.Success(response.page == response.totalPages)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, TrendingMovieEntity>
    ): TrendingMovieRemoteKeyEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                remoteKeyDao.getRemoteKeys(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, TrendingMovieEntity>
    ): TrendingMovieRemoteKeyEntity? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { movie ->
                remoteKeyDao.getRemoteKeys(id = movie.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, TrendingMovieEntity>
    ): TrendingMovieRemoteKeyEntity? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { movie ->
                remoteKeyDao.getRemoteKeys(id = movie.id)
            }
    }
}