package com.maxidev.movips.presentation.movies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.maxidev.movips.domain.model.NowPlayingMovies
import com.maxidev.movips.domain.model.PopularMovies
import com.maxidev.movips.domain.model.TopRatedMovies
import com.maxidev.movips.domain.model.UpcomingMovies
import com.maxidev.movips.presentation.components.SectionItem
import com.maxidev.movips.presentation.components.TopBarItem
import com.maxidev.movips.presentation.movies.components.NowPlayingItem
import com.maxidev.movips.presentation.movies.components.PopularMovieItem
import com.maxidev.movips.presentation.movies.components.TopRatedItem
import com.maxidev.movips.presentation.movies.components.UpcomingItem

@Composable
fun MoviesScreen(
    viewmodel: MoviesViewModel,
    onClick: (Int) -> Unit
) {
    val nowPlayingState = viewmodel.nowPlayFlow.collectAsLazyPagingItems()
    val popularState = viewmodel.popularFlow.collectAsLazyPagingItems()
    val topRatedState = viewmodel.topRatedFlow.collectAsLazyPagingItems()
    val upcomingState = viewmodel.upcomingFlow.collectAsLazyPagingItems()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBarItem(title = "Movips")
        }
    ) { innerPadding ->
        ListContent(
            now = nowPlayingState,
            popular = popularState,
            topRated = topRatedState,
            upcoming = upcomingState,
            onClick = onClick,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
private fun ListContent(
    modifier: Modifier = Modifier,
    now: LazyPagingItems<NowPlayingMovies>,
    popular: LazyPagingItems<PopularMovies>,
    topRated: LazyPagingItems<TopRatedMovies>,
    upcoming: LazyPagingItems<UpcomingMovies>,
    onClick: (Int) -> Unit
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .verticalScroll(scrollState)
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        LazyRowListItem(
            title = "Now Playing",
            model = now,
            key = now.itemKey { it.id },
            contentType = now.itemContentType { it.id },
            composable = {
                NowPlayingItem(
                    posterPath = it.posterPath,
                    backdropPath = it.backdropPath,
                    title = it.title,
                    onClick = { onClick(it.id) }
                )
            }
        )

        LazyRowListItem(
            title = "The most Popular",
            model = popular,
            key = popular.itemKey { it.id },
            contentType = popular.itemContentType { it.id },
            composable = {
                PopularMovieItem(
                    img = it.posterPath,
                    title = it.title,
                    popularity = it.popularity,
                    onClick = { onClick(it.id) }
                )
            }
        )

        LazyRowListItem(
            title = "Top Rated Movies",
            model = topRated,
            key = topRated.itemKey { it.id },
            contentType = topRated.itemContentType { it.id },
            composable = {
                TopRatedItem(
                    img = it.posterPath,
                    title = it.title,
                    voteAverage = it.voteAverage,
                    onClick = { onClick(it.id) }
                )
            }
        )

        LazyRowListItem(
            title = "Upcoming",
            model = upcoming,
            key = upcoming.itemKey { it.id },
            contentType = upcoming.itemContentType { it.id },
            composable = {
                UpcomingItem(
                    img = it.posterPath,
                    title = it.title,
                    releaseDate = it.releaseDate,
                    onClick = { onClick(it.id) }
                )
            }
        )
    }
}

@Composable
private fun <T : Any> LazyRowListItem(
    modifier: Modifier = Modifier,
    title: String,
    model: LazyPagingItems<T>,
    key: (index: Int) -> Any,
    contentType: (index: Int) -> Any?,
    lazyState: LazyListState = rememberLazyListState(),
    composable: @Composable (T) -> Unit
) {
    val lazyRemember = remember(model) { model }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(4.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SectionItem(
            title = title,
            fontSize = 28.sp
        )
        LazyRow(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth(),
            state = lazyState,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(
                count = lazyRemember.itemCount,
                key = key,
                contentType = contentType
            ) { data ->
                lazyRemember[data]?.let {
                    composable(it)
                }
            }
        }
        HorizontalDivider()
    }
}