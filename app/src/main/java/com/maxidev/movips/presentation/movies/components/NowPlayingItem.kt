package com.maxidev.movips.presentation.movies.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.maxidev.movips.presentation.components.ItemCoil

@Composable
fun NowPlayingItem(
    modifier: Modifier = Modifier,
    posterPath: String,
    backdropPath: String,
    title: String,
    onClick: () -> Unit
) {
    val roundedCornerShape = RoundedCornerShape(5)

    Box(
        modifier = modifier
            .wrapContentSize()
            .padding(4.dp)
            .clip(roundedCornerShape)
            .clickable { onClick() },
        contentAlignment = Alignment.BottomStart
    ) {
        ItemCoil(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize()
                .height(210.dp)
                .clip(roundedCornerShape)
                .graphicsLayer { compositingStrategy = CompositingStrategy.Offscreen }
                .drawWithContent {
                    val colors = listOf(Color.White, Color.Transparent)
                    drawContent()
                    drawRect(brush = Brush.verticalGradient(colors), blendMode = BlendMode.DstIn)
                },
            image = backdropPath,
            contentScale = ContentScale.Fit
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(10.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Bottom
        ) {
            ItemCoil(
                modifier = Modifier
                    .size(width = 100.dp, height = 160.dp)
                    .clip(roundedCornerShape)
                    .border(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.outline,
                        shape = roundedCornerShape
                    ),
                image = posterPath,
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = title,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .width(200.dp)
            )
        }
    }
}