package com.maxidev.movips.detail.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.maxidev.movips.detail.data.remote.DetailsRemoteApiService
import com.maxidev.movips.detail.domain.mappers.toExternalModel
import com.maxidev.movips.detail.domain.models.RecommendationsMovies
import retrofit2.HttpException
import java.io.IOException

class RecommendationsPagingSource(
    private val apiService: DetailsRemoteApiService
): PagingSource<Int, RecommendationsMovies>() {

    override fun getRefreshKey(state: PagingState<Int, RecommendationsMovies>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RecommendationsMovies> {
        return try {
            val nextPage = params.key ?: 1
            val response = apiService.getRecommendationsMovie(
                page = nextPage,
                movieId = 0
            ).toExternalModel()

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