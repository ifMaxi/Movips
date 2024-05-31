package com.maxidev.movips.detail.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Container function component [DetailContent] this contains a list with the genres or
 * categories of the series or movie.
 *
 * It is located in the middle/lower part of the screen
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GenresRowItem(
    modifier: Modifier = Modifier,
    genres: List<String>
) {
    FlowRow(
        modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(14.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        genres.forEach { genre ->
            Box(
                Modifier
                    .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(10))
                    .padding(8.dp)
            ) {
                Text(
                    text = genre,
                    modifier = Modifier.padding(3.dp)
                )
            }
        }
    }
}