package com.maxidev.movips.movies.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maxidev.movips.core.presentation.components.ItemCoil

@Composable
fun NowPlayingItem(
    modifier: Modifier = Modifier,
    posterPath: String,
    backdropPath: String,
    title: String,
    onClick: () -> Unit
) {
    val roundedCornerShape = RoundedCornerShape(10)

    ElevatedCard(
        modifier = modifier
            .fillMaxSize()
            .padding(6.dp),
        shape = roundedCornerShape,
        elevation = CardDefaults.elevatedCardElevation(6.dp)
    ) {
        Box(
            modifier = Modifier
                .wrapContentSize()
                .clip(roundedCornerShape)
                .clickable { onClick() },
            contentAlignment = Alignment.BottomStart
        ) {
            ItemCoil(
                modifier = Modifier
                    .height(390.dp)
                    .clip(roundedCornerShape)
                    .graphicsLayer { compositingStrategy = CompositingStrategy.Offscreen }
                    .drawWithContent {
                        val colors = listOf(Color.White, Color.Transparent)
                        drawContent()
                        drawRect(
                            brush = Brush.verticalGradient(colors),
                            blendMode = BlendMode.DstIn
                        )
                    },
                image = backdropPath,
                contentScale = ContentScale.Crop
            )
            Row(
                modifier = Modifier
                    .wrapContentHeight()
                    .padding(16.dp)
                    .align(Alignment.BottomStart),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                ItemCoil(
                    modifier = Modifier
                        .size(width = 120.dp, height = 180.dp)
                        .clip(roundedCornerShape)
                        .border(
                            width = 2.dp,
                            color = MaterialTheme.colorScheme.outline,
                            shape = roundedCornerShape
                        ),
                    image = posterPath,
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = title,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .align(Alignment.Bottom)
                )
            }
        }
    }
}