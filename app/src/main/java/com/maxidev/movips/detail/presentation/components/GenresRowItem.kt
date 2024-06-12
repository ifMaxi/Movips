package com.maxidev.movips.detail.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.maxidev.movips.core.presentation.ui.theme.oswald

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
            .wrapContentHeight(Alignment.Top)
            .padding(20.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        maxItemsInEachRow = 4
    ) {
        genres.forEach { genre ->
            Box(
                Modifier
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.outline,
                        shape = CircleShape
                    )
                    .padding(10.dp)
            ) {
                Text(
                    text = genre,
                    fontFamily = oswald,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(3.dp)
                )
            }
        }
    }
}