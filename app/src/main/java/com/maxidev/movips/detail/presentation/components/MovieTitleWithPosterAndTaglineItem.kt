package com.maxidev.movips.detail.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.draw.shadow
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
    val roundedCornerShape = RoundedCornerShape(5)

    Box(
        modifier = modifier
            .wrapContentSize(),
        contentAlignment = Alignment.BottomStart
    ) {
        ItemCoil(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize()
                .height(320.dp)
                .graphicsLayer { compositingStrategy = CompositingStrategy.Offscreen }
                .drawWithContent {
                    val colors = listOf(Color.White, Color.Transparent)
                    drawContent()
                    drawRect(brush = Brush.verticalGradient(colors), blendMode = BlendMode.DstIn)
                },
            image = backImage,
            contentScale = ContentScale.Crop
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
                    .size(width = 130.dp, height = 190.dp)
                    .clip(roundedCornerShape)
                    .shadow(
                        elevation = 8.dp,
                        shape = roundedCornerShape
                    )
                    .border(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.outline,
                        shape = roundedCornerShape
                    ),
                image = image,
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = title,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = tagline,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light
                )
            }
        }
    }
}