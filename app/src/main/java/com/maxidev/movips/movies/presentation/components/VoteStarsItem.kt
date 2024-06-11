package com.maxidev.movips.movies.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

@Composable
fun VoteStarsItem(
    modifier: Modifier = Modifier,
    percentage: Double
) {
    val goldColor = Color(0xFFFFD700)
    val beigeColor = Color(0xFF36454F)

    Box(
        modifier = modifier
            .padding(4.dp)
            .size(58.dp)
            .clip(CircleShape)
            .border(
                border = BorderStroke(
                    width = 4.dp,
                    color = goldColor
                ),
                shape = CircleShape
            )
            .background(beigeColor),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "⭐ ${(percentage * 10).roundToInt() / 10}",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = Color.White
        )
    }
}