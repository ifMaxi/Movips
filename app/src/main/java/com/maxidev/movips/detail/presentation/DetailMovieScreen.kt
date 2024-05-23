package com.maxidev.movips.detail.presentation

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.maxidev.movips.R
import com.maxidev.movips.core.presentation.components.ItemCoil
import com.maxidev.movips.core.presentation.components.StatusItem
import com.maxidev.movips.detail.domain.models.DetailedMovie
import com.maxidev.movips.detail.presentation.state.DetailState

@Composable
fun DetailMovieScreen(
    viewModel: DetailedMovieViewModel = hiltViewModel(),
    movieId: Int
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Int) {
        viewModel.fetchedDetails(movieId)
    }

    CheckStatus(status = state)
}

@Composable
private fun CheckStatus(status: DetailState) {

    when (status) {
        is DetailState.Error -> { StatusItem(animation = R.raw.image_error) }
        DetailState.Loading -> { StatusItem(animation = R.raw.movie_loading) }
        is DetailState.Success -> DetailContent(model = status.onSuccess)
    }
}

/**
 * Composable that will function as the detail screen, it is the container for several components.
 */
@Composable
private fun DetailContent(
    modifier: Modifier = Modifier,
    model: DetailedMovie
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        MovieTitleWithPosterAndTaglineItem(
            title = model.title,
            image = model.posterPath,
            backImage = model.backdropPath,
            tagline = model.tagline
        )
        OverviewItem(
            overview = model.overview
        )
        GenresRowItem(
            genres = model.genres
        )
        HorizontalDivider(thickness = 4.dp)
        LogoCompaniesItem(
            productionCompany = model.productionCompanies
        )
        HorizontalDivider(thickness = 4.dp)
        SpecialInfoItem(
            releaseDate = model.releaseDate,
            productionCountry = model.productionCountry,
            spokenLanguages = model.spokenLanguages,
            releaseStatus = model.releaseStatus
        )
    }
}

/**
 * Component of the containing function [DetailContent] contains a rear image that
 * applies a small blur, an image of the poster of the movie or series and the title
 * and descriptive tag.
 *
 * It will be located at the top of the detail screen.
 */
@Composable
private fun MovieTitleWithPosterAndTaglineItem(
    modifier: Modifier = Modifier,
    title: String,
    image: String,
    backImage: String,
    tagline: String
) {
    val roundedCornerShape = RoundedCornerShape(5)

    Box(
        modifier = modifier
            .wrapContentSize(),
        contentAlignment = Alignment.BottomStart
    ) {
        ItemCoil(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize()
                .height(320.dp)
                .graphicsLayer { compositingStrategy = CompositingStrategy.Offscreen }
                .drawWithContent {
                    val colors = listOf(Color.White, Color.Transparent)
                    drawContent()
                    drawRect(brush = Brush.verticalGradient(colors), blendMode = BlendMode.DstIn)
                },
            image = backImage,
            contentScale = ContentScale.Crop
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(10.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Bottom
        ) {
            ItemCoil(
                modifier = Modifier
                    .size(width = 130.dp, height = 190.dp)
                    .clip(roundedCornerShape)
                    .shadow(
                        elevation = 8.dp,
                        shape = roundedCornerShape
                    )
                    .border(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.outline,
                        shape = roundedCornerShape
                    ),
                image = image,
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = title,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = tagline,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light
                )
            }
        }
    }
}

/**
 * Container function component [DetailContent] this contains a list with the genres or
 * categories of the series or movie.
 *
 * It is located in the middle/lower part of the screen
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun GenresRowItem(
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
                    .border(3.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(8.dp))
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

/**
 * Component of the containing function [DetailContent] this will have a description of the
 * series or movie, its text is justified to be able to take advantage of each space of the
 * component and at the same time gives neatness.
 *
 * It is located in the middle part of the screen.
 */
@Composable
private fun OverviewItem(
    modifier: Modifier = Modifier,
    overview: String
) {
    Column(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(14.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "About",
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = overview,
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

/**
 * Component of the containing function [DetailContent] will show a list of names of all
 * the production companies that participated in the series/movie.
 *
 * The component is located in the middle/lower area of the screen.
 */
@Composable
private fun LogoCompaniesItem(
    modifier: Modifier = Modifier,
    productionCompany: List<String>
) {
    Column(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(14.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Text(
            text = "Producers",
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold
        )

        productionCompany.forEach { company ->
            Row(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(14.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.PlayArrow,
                    contentDescription = null
                )
                Text(
                    text = company,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Light
                )
            }
        }
    }
}

/**
 * Component of the container function [DetailContent] will display a series of data such as
 * release date, state, countries in which the series or movie was
 * produced and the available languages.
 *
 * It is located in the lower part of the screen.
 */
@Composable
private fun SpecialInfoItem(
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