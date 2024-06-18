package com.maxidev.movips.presentation.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.maxidev.movips.presentation.components.ItemCoil
import com.maxidev.movips.domain.search_domain.models.SearchMovie
import com.maxidev.movips.presentation.search.components.SearchBarItem
import kotlinx.coroutines.launch

@Composable
fun SearchMovieScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchMovieViewModel = hiltViewModel(),
    onClick: (Int) -> Unit
) {
    val query by viewModel.searchQuery.value.query
    val searchMovies = viewModel.searchedMovies.collectAsLazyPagingItems()
    var isActive by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current

    Scaffold(
        topBar = {
            SearchBarItem(
                value = query,
                onValueChange = viewModel::onSearchQueryChange,
                onSearch = {
                    scope.launch {
                        if (query.isEmpty()) {
                            isActive = false
                        } else {
                            isActive = false
                            viewModel.flowSearch(it)
                        }
                    }
                    focusManager.clearFocus()
                },
                active = isActive,
                onActiveChange = {
                    isActive = false
                },
                onClearText = {
                    viewModel.onSearchQueryChange("")
                    focusManager.clearFocus()
                }
            )
        },
        contentWindowInsets = WindowInsets.statusBars
    ) { innerPadding ->
        SearchItem(
            modifier = modifier.padding(innerPadding),
            model = searchMovies,
            onClick = onClick
        )
    }
}

@Composable
private fun SearchItem(
    modifier: Modifier = Modifier,
    model: LazyPagingItems<SearchMovie>,
    onClick: (Int) -> Unit
) {
    val rememberModel = remember(model) { model }
    val lazyState = rememberLazyGridState()

    LazyVerticalGrid(
        modifier = modifier
            .fillMaxSize(),
        columns = GridCells.Adaptive(120.dp),
        state = lazyState,
        contentPadding = PaddingValues(10.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(
            count = rememberModel.itemCount,
            key = rememberModel.itemKey { it.id }
        ) {data ->
            rememberModel[data]?.let {
                PosterSearch(
                    posterPath = it.posterPath,
                    onClick = { onClick(it.id) }
                )
            }
        }
    }
}

@Composable
private fun PosterSearch(
    modifier: Modifier = Modifier,
    posterPath: String,
    onClick: () -> Unit
) {
    val roundedClip = RoundedCornerShape(10)

    Card(
        modifier = modifier
            .padding(10.dp),
        shape = roundedClip,
        elevation = CardDefaults.cardElevation(8.dp),
        onClick = onClick
    ) {
        Box(
            modifier = Modifier
                .wrapContentSize()
                .fillMaxSize()
                .clip(roundedClip),
            contentAlignment = Alignment.Center
        ) {
            ItemCoil(
                modifier = modifier
                    .size(width = 120.dp, height = 170.dp)
                    .clip(roundedClip),
                image = posterPath,
                contentScale = ContentScale.Crop
            )
        }
    }
}