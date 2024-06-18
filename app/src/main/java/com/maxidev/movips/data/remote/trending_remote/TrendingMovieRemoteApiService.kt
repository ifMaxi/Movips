package com.maxidev.movips.data.remote.trending_remote

import com.maxidev.movips.utils.Constants.TRENDING_MOVIE
import com.maxidev.movips.data.remote.trending_remote.dto.TrendingMovieDTO
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