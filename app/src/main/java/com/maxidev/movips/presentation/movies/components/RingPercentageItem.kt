package com.maxidev.movips.presentation.movies.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun RingPercentageItem(
    modifier: Modifier = Modifier,
    percentage: Double
) {
    val percentageFormula = (percentage * 10).toInt()
    val rc = when (percentageFormula) {
        in 70..100 -> Color(0xFFA5D9B4)
        in 40..69 -> Color(0xFFF2E2BC)
        in 0..39 -> Color(0xFFF2B9BF)
        else -> Color.Transparent
    }

    Box(
        modifier = modifier
            .padding(10.dp),
        contentAlignment = Alignment.Center
    ){
        Card(
            modifier = Modifier
                .size(70.dp)
                .background(
                    color = MaterialTheme.colorScheme.background,
                    shape = CircleShape
                )
                .padding(2.dp),
            shape = CircleShape,
            border = BorderStroke(
                width = 8.dp,
                color = rc
            ),
            elevation = CardDefaults.cardElevation(6.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(70.dp)
            ) {
                Text(
                    text = "$percentageFormula%",
                    fontWeight = FontWeight.Light,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }
    }
}