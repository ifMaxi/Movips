package com.maxidev.movips.data.paging.movies_paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.maxidev.movips.data.local.MovipsDataBase
import com.maxidev.movips.data.local.movies_local.entity.TopRatedMoviesEntity
import com.maxidev.movips.data.local.movies_local.entity.remote_key_entity.TopRatedMoviesRemoteKeyEntity
import com.maxidev.movips.data.remote.movies_remote.MoviesRemoteApiService
import com.maxidev.movips.domain.movies_domain.mappers.toTopRatedEntity
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class TopRatedMoviesRemoteMediator(
    private val dataBase: MovipsDataBase,
    private val networkService: MoviesRemoteApiService
): RemoteMediator<Int, TopRatedMoviesEntity>() {

    private val topDao = dataBase.topRatedMoviesDao()
    private val remoteKeyDao = dataBase.topRatedMoviesRemoteKeyDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, TopRatedMoviesEntity>
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
            val response = networkService.getTopRatedMovies(page = loadKey)

            dataBase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    topDao.clearAll()
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
                    TopRatedMoviesRemoteKeyEntity(
                        id = data.id ?: 0,
                        nextKey = nextPage,
                        prevKey = prevPage
                    )
                }
                remoteKeyDao.addAllRemoteKeys(remoteKeys = keys!!)
                response.toTopRatedEntity()?.let { topDao.insertAll(it) }
            }

            MediatorResult.Success(endOfPaginationReached = response.page == response.totalPages)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, TopRatedMoviesEntity>
    ): TopRatedMoviesRemoteKeyEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                remoteKeyDao.getRemoteKeys(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, TopRatedMoviesEntity>
    ): TopRatedMoviesRemoteKeyEntity? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { movie ->
                remoteKeyDao.getRemoteKeys(id = movie.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, TopRatedMoviesEntity>
    ): TopRatedMoviesRemoteKeyEntity? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { movie ->
                remoteKeyDao.getRemoteKeys(id = movie.id)
            }
    }
}