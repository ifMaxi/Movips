package com.maxidev.movips.movies.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.maxidev.movips.core.presentation.components.SectionItem
import com.maxidev.movips.core.presentation.components.TopBarItem
import com.maxidev.movips.movies.domain.models.Movies
import com.maxidev.movips.movies.presentation.components.ImageCardWithRatedIcons
import com.maxidev.movips.movies.presentation.components.NowPlayingItem
import kotlin.math.absoluteValue

@Composable
fun MoviesScreen(
    viewmodel: MoviesViewModel,
    onClick: (Int) -> Unit,
    navigateToSearch: () -> Unit
) {
    val nowPlayingState = viewmodel.nowPlayFlow.collectAsLazyPagingItems()
    val popularState = viewmodel.popularFlow.collectAsLazyPagingItems()
    val topRatedState = viewmodel.topRatedFlow.collectAsLazyPagingItems()
    val upcomingState = viewmodel.upcomingFlow.collectAsLazyPagingItems()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBarItem(
                title = "Movips",
                onClick = navigateToSearch
            )
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ListContent(
    modifier: Modifier = Modifier,
    now: LazyPagingItems<Movies>,
    popular: LazyPagingItems<Movies>,
    topRated: LazyPagingItems<Movies>,
    upcoming: LazyPagingItems<Movies>,
    onClick: (Int) -> Unit
) {
    val scrollState = rememberScrollState()
    val pagerState = rememberPagerState(pageCount = { now.itemCount })

    Column(
        modifier = modifier
            .verticalScroll(scrollState)
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(4.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        SectionItem(
            title = "Now Playing",
            fontSize = 34.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
        HorizontalPager(
            modifier = Modifier
                .wrapContentSize()
                .padding(10.dp),
            state = pagerState,
            key = now.itemKey { it.id },
            pageSpacing = 40.dp,
            pageContent = { page ->
                val item = now[page]

                item?.let {
                    NowPlayingItem(
                        modifier = Modifier
                            .graphicsLayer {
                                val pageOffset = (
                                        (pagerState.currentPage - page) + pagerState
                                            .currentPageOffsetFraction
                                        ).absoluteValue

                                alpha = lerp(
                                    start = 0.5f,
                                    stop = 1f,
                                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                                )
                            },
                        posterPath = item.posterPath ?: "",
                        backdropPath = item.backdropPath ?: "",
                        title = item.title ?: "",
                        onClick = { onClick(item.id) }
                    )
                }
            }
        )

        LazyRowListItem(
            title = "The most Popular",
            model = popular,
            key = popular.itemKey { it.id },
            contentType = popular.itemContentType { it.id },
            composable = {
                ImageCardWithRatedIcons(
                    img = it.posterPath.toString(),
                    title = it.title.toString(),
                    voteAverage = it.voteAverage ?: 0.0,
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
                ImageCardWithRatedIcons(
                    img = it.posterPath.toString(),
                    title = it.title.toString(),
                    voteAverage = it.voteAverage ?: 0.0,
                    onClick = { onClick(it.id) }
                )
            }
        )

        LazyRowListItem(
            title = "Coming Soon",
            model = upcoming,
            key = upcoming.itemKey { it.id },
            contentType = upcoming.itemContentType { it.id },
            composable = {
                ImageCardWithRatedIcons(
                    img = it.posterPath.toString(),
                    title = it.title.toString(),
                    voteAverage = it.voteAverage ?: 0.0,
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
            fontSize = 34.sp
        )
        LazyRow(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth(),
            state = lazyState,
            horizontalArrangement = Arrangement.spacedBy(40.dp),
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
    }
}