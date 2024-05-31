package com.maxidev.movips.movies.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PercentageItem(
    modifier: Modifier = Modifier,
    percentage: Double
) {
    val percentageFormula = (percentage * 10).toInt()
    val emoji: Any = when (percentageFormula) {
        in 70..100 -> "💚"
        in 40..69 -> "💛"
        in 0..39 -> "❤️"
        else -> Color.Transparent
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "$percentageFormula% $emoji",
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium
        )
    }
}