package com.maxidev.movips.trending.data.remote

import com.maxidev.movips.core.utils.Constants.TRENDING_MOVIE
import com.maxidev.movips.trending.data.remote.dto.TrendingMovieDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TrendingMovieRemoteApiService {

    @GET(TRENDING_MOVIE)
    suspend fun getTrendingMovie(
        @Path("time_window") timeWindow: String = "day",
        @Query("page") page: Int
    ): TrendingMovieDTO
}