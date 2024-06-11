package com.maxidev.movips.detail.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import com.maxidev.movips.core.presentation.components.ItemCoil
import com.maxidev.movips.detail.domain.models.RecommendationsMovies

@Composable
fun RecommendationContentItem(
    modifier: Modifier = Modifier,
    model: LazyPagingItems<RecommendationsMovies>
) {
    val rememberModel = remember(model) { model }
    val lazyState = rememberLazyListState()

    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Recommended",
            fontSize = 22.sp,
            fontWeight = FontWeight.Medium,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier
                .padding(20.dp)
        )
        LazyRow(
            modifier = Modifier
                .fillMaxSize(),
            state = lazyState,
            contentPadding = PaddingValues(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(
                count = rememberModel.itemCount,
                key = rememberModel.itemKey { it.id }
            ) { data ->
                rememberModel[data]?.let {
                    PosterWithTitle(
                        posterPath = it.posterPath.toString()
                    )
                }
            }
        }
    }
}

@Composable
private fun PosterWithTitle(
    modifier: Modifier = Modifier,
    posterPath: String
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp),
        elevation = CardDefaults.elevatedCardElevation(8.dp),
        shape = RoundedCornerShape(10)
    ) {
        ItemCoil(
            image = posterPath,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(width = 130.dp, height = 190.dp)
        )
    }
}