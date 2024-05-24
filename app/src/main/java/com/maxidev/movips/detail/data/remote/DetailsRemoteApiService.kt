package com.maxidev.movips.detail.data.remote

import com.maxidev.movips.core.utils.Constants
import com.maxidev.movips.detail.data.remote.dto.DetailedMovieDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailsRemoteApiService {

    @GET(Constants.DETAIL_MOVIE)
    suspend fun getDetailedMovie(
        @Path("movie_id") movieId: Int
    ): DetailedMovieDTO
}