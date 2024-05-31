package com.maxidev.movips.detail.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
    spokenLanguages: List<String>
) {
    Column(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(14.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
        horizontalAlignment = Alignment.Start
    ) {
        val informationList = listOf(
            Pair("Release Date: ", releaseDate),
            Pair("Release Status: ", releaseStatus),
            Pair("Production Country: ", productionCountry.joinToString()),
            Pair("Languages: ", spokenLanguages.joinToString())
        )

        informationList.forEach { word ->
            val buildStrings = buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp
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
            )
            HorizontalDivider(
                modifier = Modifier
                    .width(200.dp)
                    .align(Alignment.Start)
            )
        }
    }
}