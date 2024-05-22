package com.maxidev.movips.data.remote

import com.maxidev.movips.data.remote.dto.details.DetailedMovieDTO
import com.maxidev.movips.data.remote.dto.movie.NowPlayingAndUpcomingMovieDTO
import com.maxidev.movips.data.remote.dto.movie.PopularAndTopRatedMoviesDTO
import com.maxidev.movips.utils.Constants.DETAIL_MOVIE
import com.maxidev.movips.utils.Constants.NOW_PLAYING_MOVIES
import com.maxidev.movips.utils.Constants.POPULAR_MOVIES
import com.maxidev.movips.utils.Constants.TOP_RATED_MOVIES
import com.maxidev.movips.utils.Constants.UPCOMING_MOVIES
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RemoteApiService {

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