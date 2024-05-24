package com.maxidev.movips.search.data.remote

import com.maxidev.movips.core.utils.Constants.SEARCH_MOVIE
import com.maxidev.movips.search.data.remote.dto.SearchMovieDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchRemoteApiService {

    @GET(SEARCH_MOVIE)
    suspend fun getSearchMovie(
        @Query("page") page: Int,
        @Query("query") query: String
    ): SearchMovieDTO
}