package com.maxidev.movips.data.repository

import androidx.paging.PagingData
import com.maxidev.movips.domain.model.NowPlayingMovies
import com.maxidev.movips.domain.model.PopularMovies
import com.maxidev.movips.domain.model.TopRatedMovies
import com.maxidev.movips.domain.model.UpcomingMovies
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    fun fetchNowPlayingMovies(): Flow<PagingData<NowPlayingMovies>>

    fun fetchTopRatedMovies(): Flow<PagingData<TopRatedMovies>>

    fun fetchPopularMovies(): Flow<PagingData<PopularMovies>>

    fun fetchUpcomingMovies(): Flow<PagingData<UpcomingMovies>>
}