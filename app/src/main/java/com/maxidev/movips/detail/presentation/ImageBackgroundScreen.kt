package com.maxidev.movips.detail.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.maxidev.movips.R
import com.maxidev.movips.core.presentation.components.ItemCoil
import com.maxidev.movips.core.presentation.components.SectionItem
import com.maxidev.movips.core.presentation.components.StatusItem
import com.maxidev.movips.detail.domain.models.ImageMovie
import com.maxidev.movips.detail.presentation.state.ImageState

@Composable
fun ImageBackgroundScreen(
    viewModel: ImageBackViewModel = hiltViewModel(),
    movieId: Int
) {
    val state by viewModel.imageState.collectAsStateWithLifecycle()

    LaunchedEffect(Int) {
        viewModel.fetchedImages(movieId)
    }

    ImageStatus(state = state)
}

@Composable
private fun ImageStatus(state: ImageState) {

    when (state) {
        is ImageState.Error -> { StatusItem(animation = R.raw.image_error) }
        is ImageState.Success -> {
            ImageContentList(img = state.onSuccess)
        }
    }
}

@Composable
private fun ImageContentList(
    modifier: Modifier = Modifier,
    img: List<ImageMovie>
) {
    val lazyListState = rememberLazyListState()
    val rememberImages = remember(img) { img }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(WindowInsets.statusBars.asPaddingValues()),
        state = lazyListState,
        contentPadding = PaddingValues(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            SectionItem(title = "Images", fontSize = 34.sp)
        }
        items(
            items = rememberImages,
            key = { it.filePath },
            contentType = { it.filePath }
        ) { data ->
            WallpaperImage(
                img = data.filePath
            )
        }
    }
}

@Composable
private fun WallpaperImage(
    modifier: Modifier = Modifier,
    img: String
) {
    ElevatedCard(
        modifier = modifier
            .wrapContentSize()
            .padding(10.dp),
        shape = RoundedCornerShape(10),
        elevation = CardDefaults.elevatedCardElevation(8.dp)
    ) {
        ItemCoil(
            image = img,
            contentScale = ContentScale.Crop
        )
    }
}