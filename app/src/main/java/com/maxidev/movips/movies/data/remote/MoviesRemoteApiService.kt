package com.maxidev.movips.movies.data.remote

import com.maxidev.movips.detail.data.remote.dto.DetailedMovieDTO
import com.maxidev.movips.movies.data.remote.dto.NowPlayingAndUpcomingMovieDTO
import com.maxidev.movips.movies.data.remote.dto.PopularAndTopRatedMoviesDTO
import com.maxidev.movips.core.utils.Constants.DETAIL_MOVIE
import com.maxidev.movips.core.utils.Constants.NOW_PLAYING_MOVIES
import com.maxidev.movips.core.utils.Constants.POPULAR_MOVIES
import com.maxidev.movips.core.utils.Constants.TOP_RATED_MOVIES
import com.maxidev.movips.core.utils.Constants.UPCOMING_MOVIES
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesRemoteApiService {

    @GET(NOW_PLAYING_MOVIES)
    suspend fun getNowPlayingMovies(
        @Query("page") page: Int
    ): NowPlayingAndUpcomingMovieDTO

    @GET(UPCOMING_MOVIES)
    suspend fun getUpcomingMovies(
        @Query("page") page: Int
    ): NowPlayingAndUpcomingMovieDTO

    @GET(POPULAR_MOVIES)
    suspend fun getPopularMovies(
        @Query("page") page: Int
    ): PopularAndTopRatedMoviesDTO

    @GET(TOP_RATED_MOVIES)
    suspend fun getTopRatedMovies(
        @Query("page") page: Int
    ): PopularAndTopRatedMoviesDTO

    @GET(DETAIL_MOVIE)
    suspend fun getDetailedMovie(
        @Path("movie_id") movieId: Int
    ): DetailedMovieDTO
}