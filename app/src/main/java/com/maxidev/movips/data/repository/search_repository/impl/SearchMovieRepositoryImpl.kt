package com.maxidev.movips.data.repository.search_repository.impl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.filter
import com.maxidev.movips.data.paging.search_paging.SearchMoviePagingSource
import com.maxidev.movips.data.remote.search_remote.SearchRemoteApiService
import com.maxidev.movips.data.repository.search_repository.SearchMovieRepository
import com.maxidev.movips.domain.search_domain.models.SearchMovie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchMovieRepositoryImpl @Inject constructor(
    private val api: SearchRemoteApiService
): SearchMovieRepository {

    override suspend fun fetchSearchedMovies(query: String): Flow<PagingData<SearchMovie>> =
        Pager(
            config = PagingConfig(
                pageSize = 1,
                prefetchDistance = 1 * 2
            ),
            pagingSourceFactory = {
                SearchMoviePagingSource(
                    query = query,
                    api = api
                )
            }
        ).flow
            .map {
                val filtering = mutableSetOf<Int>()

                it.filter { img ->
                    if (filtering.contains(img.id)) {
                        false
                    } else {
                        filtering.add(img.id)
                    }
                }
            }
}