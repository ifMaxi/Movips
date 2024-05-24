package com.maxidev.movips.search.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.maxidev.movips.search.data.remote.SearchRemoteApiService
import com.maxidev.movips.search.domain.mappers.toMovieExternalModel
import com.maxidev.movips.search.domain.models.SearchMovie
import retrofit2.HttpException
import java.io.IOException

class SearchMoviePagingSource(
    private val api: SearchRemoteApiService,
    private val query: String
): PagingSource<Int, SearchMovie>() {

    override fun getRefreshKey(state: PagingState<Int, SearchMovie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchMovie> {
        return try {
            val nextPage = params.key ?: 1
            val response = api.getSearchMovie(
                query = query,
                page = nextPage
            ).toMovieExternalModel()

            LoadResult.Page(
                data = response.orEmpty(),
                prevKey = if (nextPage == 1) null else nextPage.minus(1),
                nextKey = if (response.isNullOrEmpty()) null else nextPage.plus(1)
            )

        } catch (ioException: IOException) {
            LoadResult.Error(ioException)
        } catch (httpException: HttpException) {
            LoadResult.Error(httpException)
        }
    }
}