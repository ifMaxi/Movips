package com.maxidev.movips.movies.presentation.components

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maxidev.movips.core.presentation.components.ItemCoil

@Composable
fun PopularMovieItem(
    modifier: Modifier = Modifier,
    img: String,
    title: String,
    popularity: Double,
    onClick: () -> Unit
) {
    val roundedClip = RoundedCornerShape(5)

    Column(
        modifier = Modifier
            .height(400.dp)
            .clip(roundedClip),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = modifier
                .width(200.dp)
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.outline,
                    shape = roundedClip
                ),
            shape = roundedClip,
            elevation = CardDefaults.cardElevation(6.dp),
            onClick = onClick
        ) {
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .clip(roundedClip),
                contentAlignment = Alignment.Center
            ) {
                ItemCoil(
                    modifier = modifier
                        .size(width = 200.dp, height = 300.dp)
                        .clip(roundedClip),
                    image = img,
                    contentScale = ContentScale.Crop
                )
                PopularityNumber(
                    popularity = popularity,
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                )
            }
            Spacer(modifier = Modifier.height(14.dp))
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(10.dp)
            )
        }
    }
}

@Composable
private fun PopularityNumber(
    modifier: Modifier = Modifier,
    popularity: Double
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(10.dp)
            .clip(CircleShape)
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = CircleShape
            )
            .background(MaterialTheme.colorScheme.background),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Score: ${popularity.toInt()}",
            fontSize = 18.sp,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}