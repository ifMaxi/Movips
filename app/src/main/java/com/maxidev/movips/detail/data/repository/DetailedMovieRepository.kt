package com.maxidev.movips.detail.data.repository

import androidx.paging.PagingData
import com.maxidev.movips.detail.domain.models.CreditsMovie
import com.maxidev.movips.detail.domain.models.DetailedMovie
import com.maxidev.movips.detail.domain.models.ImageMovie
import com.maxidev.movips.detail.domain.models.RecommendationsMovies
import kotlinx.coroutines.flow.Flow

interface DetailedMovieRepository {

    suspend fun fetchedDetails(movieId: Int): DetailedMovie

    suspend fun fetchedCredits(movieId: Int): List<CreditsMovie>

    suspend fun fetchedImages(movieId: Int): List<ImageMovie>

    fun fetchedRecommendations(movieId: Int): Flow<PagingData<RecommendationsMovies>>
}