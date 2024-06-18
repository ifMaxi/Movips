package com.maxidev.movips.trending.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.maxidev.movips.core.presentation.components.SectionItem
import com.maxidev.movips.trending.domain.models.TrendingMovie
import com.maxidev.movips.trending.presentation.components.PosterWithTitleAndStarsItem

@Composable
fun TrendingMoviesScreen(
    viewModel: TrendingMovieViewModel = hiltViewModel(),
    onClick: (Int) -> Unit
) {
    val trendingState = viewModel.trendingFlow.collectAsLazyPagingItems()

    TrendingList(
        lazyPaging = trendingState,
        onClick = onClick
    )
}

@Composable
private fun TrendingList(
    modifier: Modifier = Modifier,
    lazyPaging: LazyPagingItems<TrendingMovie>,
    onClick: (Int) -> Unit
) {
    val rememberPaging = remember(lazyPaging) { lazyPaging }
    val lazyListState = rememberLazyListState()

    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        state = lazyListState,
        contentPadding = PaddingValues(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item {
            SectionItem(
                title = "Trending",
                fontSize = 30.sp
            )
        }
        items(
            count = rememberPaging.itemCount,
            key = rememberPaging.itemKey { it.id },
            contentType = rememberPaging.itemContentType { it.id }
        ) { data ->
            rememberPaging[data]?.let {
                PosterWithTitleAndStarsItem(
                    posterPath = it.posterPath.toString(),
                    backdropPath = it.backdropPath.toString(),
                    title = it.title.toString(),
                    voteAverage = it.voteAverage ?: 0.0,
                    onClick = { onClick(it.id) }
                )
            }
        }
    }
}