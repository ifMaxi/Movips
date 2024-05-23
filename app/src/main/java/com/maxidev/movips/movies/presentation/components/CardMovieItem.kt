package com.maxidev.movips.movies.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.maxidev.movips.core.presentation.components.ItemCoil

@Composable
fun CardMovieItem(
    modifier: Modifier = Modifier,
    img: String,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        shape = RoundedCornerShape(5),
        elevation = CardDefaults.cardElevation(6.dp),
        onClick = onClick
    ) {
        ItemCoil(
            modifier = modifier
                .size(width = 200.dp, height = 300.dp)
                .clip(RoundedCornerShape(5)),
            image = img,
            contentScale = ContentScale.Crop
        )
    }
}