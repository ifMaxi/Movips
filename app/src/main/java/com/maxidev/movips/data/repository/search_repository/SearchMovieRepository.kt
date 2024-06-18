package com.maxidev.movips.data.repository.search_repository

import androidx.paging.PagingData
import com.maxidev.movips.domain.search_domain.models.SearchMovie
import kotlinx.coroutines.flow.Flow

interface SearchMovieRepository {

    suspend fun fetchSearchedMovies(query: String): Flow<PagingData<SearchMovie>>
}