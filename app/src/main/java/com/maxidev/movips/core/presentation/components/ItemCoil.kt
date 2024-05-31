package com.maxidev.movips.core.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.maxidev.movips.R
import kotlinx.coroutines.Dispatchers

@Composable
fun ItemCoil(
    modifier: Modifier = Modifier,
    image: String,
    contentScale: ContentScale
) {
    val context = LocalContext.current
    val imageRequest = ImageRequest.Builder(context)
        .data("https://image.tmdb.org/t/p/original/${image}")
        .crossfade(true)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .diskCachePolicy(CachePolicy.ENABLED)
        .networkCachePolicy(CachePolicy.ENABLED)
        .allowHardware(true)
        .fetcherDispatcher(Dispatchers.IO)
        .error(R.drawable.not_found)
        .build()

    Box(
        modifier = modifier
            .wrapContentSize(),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            modifier = Modifier,
            model = imageRequest,
            contentDescription = null,
            contentScale = contentScale
        )
    }
}