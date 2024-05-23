package com.maxidev.movips.detail.data.repository

import com.maxidev.movips.detail.domain.models.DetailedMovie

interface DetailedMovieRepository {

    suspend fun fetchedDetails(movieId: Int): DetailedMovie
}