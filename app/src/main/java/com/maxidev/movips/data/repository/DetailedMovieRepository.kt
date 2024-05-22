package com.maxidev.movips.data.repository

import com.maxidev.movips.domain.model.DetailedMovie

interface DetailedMovieRepository {

    suspend fun fetchedDetails(movieId: Int): DetailedMovie
}