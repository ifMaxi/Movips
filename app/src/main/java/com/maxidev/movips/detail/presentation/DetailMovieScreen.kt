package com.maxidev.movips.detail.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.maxidev.movips.R
import com.maxidev.movips.core.presentation.components.StatusItem
import com.maxidev.movips.detail.domain.models.DetailedMovie
import com.maxidev.movips.detail.presentation.components.CategoryInformationItem
import com.maxidev.movips.detail.presentation.components.GenresRowItem
import com.maxidev.movips.detail.presentation.components.LogoCompaniesItem
import com.maxidev.movips.detail.presentation.components.MovieTitleWithPosterAndTaglineItem
import com.maxidev.movips.detail.presentation.components.OverviewItem
import com.maxidev.movips.detail.presentation.components.SpecialInfoItem
import com.maxidev.movips.detail.presentation.state.DetailState

@Composable
fun DetailMovieScreen(
    viewModel: DetailedMovieViewModel = hiltViewModel(),
    movieId: Int,
    onImagesClick: (Int) -> Unit,
    onCreditsClick: (Int) -> Unit
) {
    val detailState by viewModel.detailState.collectAsStateWithLifecycle()

    LaunchedEffect(Int) {
        viewModel.fetchedDetails(movieId)
    }

    CheckStatus(
        detailStatus = detailState,
        onImagesClick = { onImagesClick(movieId) },
        onCreditsClick = { onCreditsClick(movieId) }
    )
}

@Composable
private fun CheckStatus(
    detailStatus: DetailState,
    onImagesClick: () -> Unit,
    onCreditsClick: () -> Unit
) {

    when (detailStatus) {
        is DetailState.Error -> { StatusItem(animation = R.raw.image_error) }
        DetailState.Loading -> { StatusItem(animation = R.raw.movie_loading) }
        is DetailState.Success -> DetailContent(
            model = detailStatus.onSuccess,
            onImagesClick = { onImagesClick() },
            onCreditsClick = { onCreditsClick() }
        )
    }
}

/**
 * Composable that will function as the detail screen, it is the container for several components.
 */
@Composable
private fun DetailContent(
    modifier: Modifier = Modifier,
    model: DetailedMovie,
    onImagesClick: () -> Unit,
    onCreditsClick: () -> Unit
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
        CategoryInformationItem(
            onClick = onImagesClick,
            text = "Images"
        )
        HorizontalDivider(thickness = 4.dp)
        CategoryInformationItem(
            onClick = onCreditsClick,
            text = "Main cast"
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