package com.maxidev.movips.trending.data.repositoy

import androidx.paging.PagingData
import com.maxidev.movips.trending.domain.models.TrendingMovie
import kotlinx.coroutines.flow.Flow

interface TrendingMovieRepository {

    fun fetchTrendingMovies(): Flow<PagingData<TrendingMovie>>
}