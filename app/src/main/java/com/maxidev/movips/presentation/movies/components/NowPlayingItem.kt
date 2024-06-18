package com.maxidev.movips.presentation.movies.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maxidev.movips.presentation.components.ItemCoil
import com.maxidev.movips.presentation.ui.theme.bebasNeue

@Composable
fun NowPlayingItem(
    modifier: Modifier = Modifier,
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
                    .fillMaxSize()
                    .clip(roundedCornerShape)
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
                    },
                image = backdropPath,
                contentScale = ContentScale.Fit
            )
            Text(
                text = title,
                fontSize = 24.sp,
                fontFamily = bebasNeue,
                modifier = Modifier
                    .width(300.dp)
                    .padding(20.dp)
            )
        }
    }
}