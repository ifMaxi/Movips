package com.maxidev.movips.presentation.movies.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maxidev.movips.presentation.ui.theme.bebasNeue
import kotlin.math.roundToInt

@Composable
fun VoteStarsItem(
    modifier: Modifier = Modifier,
    percentage: Double
) {
    Box(
        modifier = modifier
            .padding(4.dp)
            .size(58.dp)
            .clip(CircleShape)
            .border(
                border = BorderStroke(
                    width = 4.dp,
                    color = MaterialTheme.colorScheme.outline
                ),
                shape = CircleShape
            )
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "⭐ ${(percentage * 10).roundToInt() / 10}",
            fontSize = 18.sp,
            fontFamily = bebasNeue,
            fontWeight = FontWeight.Medium
        )
    }
}