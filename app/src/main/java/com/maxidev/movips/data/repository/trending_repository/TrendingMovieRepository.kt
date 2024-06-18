package com.maxidev.movips.data.repository.trending_repository

import androidx.paging.PagingData
import com.maxidev.movips.domain.trending_domain.models.TrendingMovie
import kotlinx.coroutines.flow.Flow

interface TrendingMovieRepository {

    fun fetchTrendingMovies(): Flow<PagingData<TrendingMovie>>
}