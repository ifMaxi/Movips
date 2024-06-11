package com.maxidev.movips.detail.data.repository

import androidx.paging.PagingData
import com.maxidev.movips.detail.domain.models.CreditsMovie
import com.maxidev.movips.detail.domain.models.DetailedMovie
import com.maxidev.movips.detail.domain.models.RecommendationsMovies
import kotlinx.coroutines.flow.Flow

interface DetailedMovieRepository {

    suspend fun fetchedDetails(movieId: Int): DetailedMovie

    fun fetchedCredits(movieId: Int): Flow<PagingData<CreditsMovie>>

    fun fetchedRecommendations(movieId: Int): Flow<PagingData<RecommendationsMovies>>
}