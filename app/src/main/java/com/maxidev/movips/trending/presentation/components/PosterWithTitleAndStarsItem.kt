package com.maxidev.movips.trending.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maxidev.movips.core.presentation.components.ItemCoil
import com.maxidev.movips.core.presentation.ui.theme.bebasNeue
import com.maxidev.movips.movies.presentation.components.VoteStarsItem

@Composable
fun PosterWithTitleAndStarsItem(
    modifier: Modifier = Modifier,
    posterPath: String,
    backdropPath: String,
    title: String,
    voteAverage: Double,
    onClick: () -> Unit
) {
    val roundedCorner = RoundedCornerShape(10)

    ElevatedCard(
        modifier = modifier
            .fillMaxSize()
            .padding(6.dp),
        elevation = CardDefaults.elevatedCardElevation(6.dp),
        shape = roundedCorner
    ) {
        Box(
            modifier = Modifier
                .wrapContentSize()
                .clip(roundedCorner)
                .clickable { onClick() },
            contentAlignment = Alignment.BottomStart
        ) {
            ItemCoil(
                image = backdropPath,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        compositingStrategy = CompositingStrategy.Offscreen
                    }
                    .drawWithContent {
                        val colors = listOf(
                            Color.Black,
                            Color.Black,
                            Color.Transparent,
                            Color.Transparent
                        )
                        drawContent()
                        drawRect(
                            brush = Brush.verticalGradient(
                                colors = colors,
                                startY = 0f,
                                endY = 1000f
                            ),
                            blendMode = BlendMode.DstIn
                        )
                    }
            )
            VoteStarsItem(
                percentage = voteAverage,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(6.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                ItemCoil(
                    image = posterPath,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(width = 100.dp, height = 150.dp)
                        .clip(roundedCorner)
                )
                Text(
                    text = title,
                    fontSize = 24.sp,
                    fontFamily = bebasNeue,
                )
            }
        }
    }
}