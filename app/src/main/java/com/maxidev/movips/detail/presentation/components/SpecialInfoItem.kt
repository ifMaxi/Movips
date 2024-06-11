package com.maxidev.movips.detail.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

/**
 * Component of the container function [DetailContent] will display a series of data such as
 * release date, state, countries in which the series or movie was
 * produced and the available languages.
 *
 * It is located in the lower part of the screen.
 */
@Composable
fun SpecialInfoItem(
    modifier: Modifier = Modifier,
    releaseDate: String,
    releaseStatus: String,
    productionCountry: List<String>,
    spokenLanguages: List<String>,
    budget: String,
    revenue: String,
    voteAverage: Double
) {
    val informationList = listOf(
        Pair("Release Date: ", releaseDate),
        Pair("Release Status: ", releaseStatus),
        Pair("Production Country: ", productionCountry.joinToString()),
        Pair("Languages: ", spokenLanguages.joinToString()),
        Pair("Budget: ", budget),
        Pair("Revenue: ", revenue),
        Pair("Score: ", "⭐ ${(voteAverage * 10).roundToInt() / 10}")
    )

    Column(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(20.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Additional Information",
            fontSize = 22.sp,
            fontWeight = FontWeight.Medium,
            textDecoration = TextDecoration.Underline
        )
        Spacer(modifier = Modifier.height(20.dp))
        informationList.forEach { word ->
            val buildStrings = buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                ) {
                    append(word.first)
                }
                withStyle(SpanStyle(fontWeight = FontWeight.Light)) {
                    append(word.second)
                }
            }
            Text(
                text = buildStrings,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            )
        }
    }
}