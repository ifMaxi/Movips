package com.maxidev.movips.data.repository.detail_repository

import androidx.paging.PagingData
import com.maxidev.movips.domain.detail_domain.models.CreditsMovie
import com.maxidev.movips.domain.detail_domain.models.DetailedMovie
import com.maxidev.movips.domain.detail_domain.models.RecommendationsMovies
import kotlinx.coroutines.flow.Flow

interface DetailedMovieRepository {

    suspend fun fetchedDetails(movieId: Int): DetailedMovie

    fun fetchedCredits(movieId: Int): Flow<PagingData<CreditsMovie>>

    fun fetchedRecommendations(movieId: Int): Flow<PagingData<RecommendationsMovies>>
}