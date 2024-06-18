package com.maxidev.movips.data.remote.search_remote

import com.maxidev.movips.utils.Constants.SEARCH_MOVIE
import com.maxidev.movips.data.remote.search_remote.dto.SearchMovieDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchRemoteApiService {

    @GET(SEARCH_MOVIE)
    suspend fun getSearchMovie(
        @Query("page") page: Int,
        @Query("query") query: String
    ): SearchMovieDTO
}