package com.maxidev.movips.detail.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maxidev.movips.core.presentation.ui.theme.oswald

/**
 * Component of the containing function [DetailContent] this will have a description of the
 * series or movie, its text is justified to be able to take advantage of each space of the
 * component and at the same time gives neatness.
 *
 * It is located in the middle part of the screen.
 */
@Composable
fun OverviewItem(
    modifier: Modifier = Modifier,
    overview: String
) {
    Column(
        modifier = modifier
            .wrapContentHeight(Alignment.Top)
            .fillMaxWidth()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = "About",
            fontSize = 24.sp,
            fontFamily = oswald,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .align(Alignment.Start)
        )
        Text(
            text = overview,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.Start)
        )
    }
}