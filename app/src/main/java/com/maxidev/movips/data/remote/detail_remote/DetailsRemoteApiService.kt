package com.maxidev.movips.data.remote.detail_remote

import com.maxidev.movips.utils.Constants
import com.maxidev.movips.data.remote.detail_remote.dto.CreditsMovieDTO
import com.maxidev.movips.data.remote.detail_remote.dto.DetailedMovieDTO
import com.maxidev.movips.data.remote.detail_remote.dto.RecommendationsMovieDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DetailsRemoteApiService {

    @GET(Constants.DETAIL_MOVIE)
    suspend fun getDetailedMovie(
        @Path("movie_id") movieId: Int
    ): DetailedMovieDTO

    @GET(Constants.CREDITS_MOVIE)
    suspend fun getCreditsMovie(
        @Path("movie_id") movieId: Int
    ): CreditsMovieDTO

    @GET(Constants.RECOMMENDATIONS_MOVIE)
    suspend fun getRecommendationsMovie(
        @Path("movie_id") movieId: Int,
        @Query("page") page: Int
    ): RecommendationsMovieDTO
}