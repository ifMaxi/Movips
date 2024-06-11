package com.maxidev.movips.detail.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.maxidev.movips.detail.data.remote.DetailsRemoteApiService
import com.maxidev.movips.detail.domain.mappers.toExternalModel
import com.maxidev.movips.detail.domain.models.CreditsMovie
import retrofit2.HttpException
import java.io.IOException

class CreditsPagingSource (
    private val apiService: DetailsRemoteApiService,
    private val movieId: Int
): PagingSource<Int, CreditsMovie>() {

    override fun getRefreshKey(state: PagingState<Int, CreditsMovie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CreditsMovie> {
        return  try {
            val nextPage = params.key ?: 1
            val response = apiService.getCreditsMovie(movieId).toExternalModel()

            LoadResult.Page(
                data = response.orEmpty(),
                prevKey = null,
                nextKey = if (response.isNullOrEmpty()) null else null
            )

        } catch (ioException: IOException) {
            LoadResult.Error(ioException)
        } catch (httpException: HttpException) {
            LoadResult.Error(httpException)
        }
    }
}