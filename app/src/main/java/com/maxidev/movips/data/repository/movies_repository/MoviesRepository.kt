package com.maxidev.movips.data.repository.movies_repository

import androidx.paging.PagingData
import com.maxidev.movips.domain.movies_domain.models.Movies
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    fun fetchNowPlayingMovies(): Flow<PagingData<Movies>>

    fun fetchTopRatedMovies(): Flow<PagingData<Movies>>

    fun fetchPopularMovies(): Flow<PagingData<Movies>>

    fun fetchUpcomingMovies(): Flow<PagingData<Movies>>
}