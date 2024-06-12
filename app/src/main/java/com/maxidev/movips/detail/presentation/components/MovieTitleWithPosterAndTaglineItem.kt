package com.maxidev.movips.detail.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maxidev.movips.core.presentation.components.ItemCoil
import com.maxidev.movips.core.presentation.ui.theme.bebasNeue

/**
 * Component of the containing function [DetailContent] contains a rear image that
 * applies a small blur, an image of the poster of the movie or series and the title
 * and descriptive tag.
 *
 * It will be located at the top of the detail screen.
 */
@Composable
fun MovieTitleWithPosterAndTaglineItem(
    modifier: Modifier = Modifier,
    title: String,
    image: String,
    backImage: String,
    tagline: String
) {
    val roundedCornerShape = RoundedCornerShape(10)

    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        ItemCoil(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer { compositingStrategy = CompositingStrategy.Offscreen }
                .drawWithContent {
                    val colors = listOf(Color.White, Color.Transparent)
                    drawContent()
                    drawRect(
                        brush = Brush.verticalGradient(colors),
                        blendMode = BlendMode.DstIn
                    )
                },
            image = backImage,
            contentScale = ContentScale.Fit
        )
        Column(
            modifier = Modifier
                .wrapContentSize(Alignment.BottomCenter)
                .padding(top = 100.dp, bottom = 10.dp, start = 10.dp, end = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            ItemCoil(
                modifier = Modifier
                    .size(width = 200.dp, height = 300.dp)
                    .clip(roundedCornerShape),
                image = image,
                contentScale = ContentScale.Crop
            )
            Text(
                text = title,
                fontSize = 28.sp,
                fontFamily = bebasNeue,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = tagline,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Light
            )
        }
    }
}