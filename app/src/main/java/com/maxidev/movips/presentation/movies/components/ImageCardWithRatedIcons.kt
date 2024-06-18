package com.maxidev.movips.presentation.movies.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.maxidev.movips.presentation.components.ItemCoil
import com.maxidev.movips.presentation.ui.theme.bebasNeue

@Composable
fun ImageCardWithRatedIcons(
    modifier: Modifier = Modifier,
    img: String,
    title: String,
    voteAverage: Double,
    onClick: () -> Unit
) {
    val roundedClip = RoundedCornerShape(10)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        ElevatedCard(
            modifier = Modifier
                .wrapContentSize(),
            shape = RoundedCornerShape(10),
            elevation = CardDefaults.elevatedCardElevation(6.dp)
        ) {
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .clip(roundedClip)
                    .clickable { onClick() },
                contentAlignment = Alignment.Center
            ) {
                ItemCoil(
                    modifier = modifier
                        .size(width = 200.dp, height = 300.dp)
                        .clip(roundedClip)
                        .graphicsLayer { compositingStrategy = CompositingStrategy.Offscreen }
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
                                    endY = 1400f
                                ),
                                blendMode = BlendMode.DstIn
                            )
                        },
                    image = img,
                    contentScale = ContentScale.Crop
                )
                VoteStarsItem(
                    percentage = voteAverage,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                )
                Text(
                    text = title,
                    fontFamily = bebasNeue,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .width(150.dp)
                        .padding(10.dp)
                        .align(Alignment.BottomStart)
                )
            }
        }
    }
}