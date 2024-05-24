package com.maxidev.movips.search.data.repository

import androidx.paging.PagingData
import com.maxidev.movips.search.domain.models.SearchMovie
import kotlinx.coroutines.flow.Flow

interface SearchMovieRepository {

    suspend fun fetchSearchedMovies(query: String): Flow<PagingData<SearchMovie>>
}