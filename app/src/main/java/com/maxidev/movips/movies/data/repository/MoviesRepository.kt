package com.maxidev.movips.movies.data.repository

import androidx.paging.PagingData
import com.maxidev.movips.movies.domain.models.NowPlayingMovies
import com.maxidev.movips.movies.domain.models.PopularMovies
import com.maxidev.movips.movies.domain.models.TopRatedMovies
import com.maxidev.movips.movies.domain.models.UpcomingMovies
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    fun fetchNowPlayingMovies(): Flow<PagingData<NowPlayingMovies>>

    fun fetchTopRatedMovies(): Flow<PagingData<TopRatedMovies>>

    fun fetchPopularMovies(): Flow<PagingData<PopularMovies>>

    fun fetchUpcomingMovies(): Flow<PagingData<UpcomingMovies>>
}