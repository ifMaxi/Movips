package com.maxidev.movips.detail.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.waterfall
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.maxidev.movips.R
import com.maxidev.movips.core.presentation.components.StatusItem
import com.maxidev.movips.detail.domain.models.CreditsMovie
import com.maxidev.movips.detail.domain.models.DetailedMovie
import com.maxidev.movips.detail.domain.models.RecommendationsMovies
import com.maxidev.movips.detail.presentation.components.CompaniesItem
import com.maxidev.movips.detail.presentation.components.CreditContentItem
import com.maxidev.movips.detail.presentation.components.GenresRowItem
import com.maxidev.movips.detail.presentation.components.MovieTitleWithPosterAndTaglineItem
import com.maxidev.movips.detail.presentation.components.OverviewItem
import com.maxidev.movips.detail.presentation.components.RecommendationContentItem
import com.maxidev.movips.detail.presentation.components.SpecialInfoItem
import com.maxidev.movips.detail.presentation.state.DetailState

@Composable
fun DetailMovieScreen(
    viewModel: DetailedMovieViewModel = hiltViewModel(),
    movieId: Int,
) {
    val detailState by viewModel.detailState.collectAsStateWithLifecycle()
    val creditState = viewModel.pagerCredits(movieId).collectAsLazyPagingItems()
    val recommendationState = viewModel.pagerRecommendations(movieId).collectAsLazyPagingItems()

    LaunchedEffect(Int) {
        viewModel.fetchedDetails(movieId)
    }

    CheckStatus(
        detailStatus = detailState,
        creditModel = creditState,
        recommendedModel = recommendationState,
        modifier = Modifier.windowInsetsPadding(WindowInsets.waterfall)
    )
}

@Composable
private fun CheckStatus(
    modifier: Modifier = Modifier,
    detailStatus: DetailState,
    creditModel: LazyPagingItems<CreditsMovie>,
    recommendedModel: LazyPagingItems<RecommendationsMovies>,
) {

    when (detailStatus) {
        is DetailState.Error -> { StatusItem(animation = R.raw.image_error) }
        DetailState.Loading -> { StatusItem(animation = R.raw.movie_loading) }
        is DetailState.Success -> DetailContent(
            detailedModel = detailStatus.onSuccess,
            creditModel = creditModel,
            recommendedModel = recommendedModel,
            modifier = modifier
        )
    }
}

/**
 * Composable that will function as the detail screen, it is the container for several components.
 */
@Composable
private fun DetailContent(
    modifier: Modifier = Modifier,
    detailedModel: DetailedMovie,
    creditModel: LazyPagingItems<CreditsMovie>,
    recommendedModel: LazyPagingItems<RecommendationsMovies>
) {
    val lazyState = rememberLazyListState()

    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        state = lazyState,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            MovieTitleWithPosterAndTaglineItem(
                title = detailedModel.title,
                image = detailedModel.posterPath,
                backImage = detailedModel.backdropPath,
                tagline = detailedModel.tagline
            )
        }
        item {
            GenresRowItem(
                genres = detailedModel.genres
            )
        }
        item {
            OverviewItem(
                overview = detailedModel.overview
            )
        }
        item {
            CreditContentItem(
                model = creditModel
            )
        }
        item {
            CompaniesItem(
                productionCompany = detailedModel.productionCompanies
            )
        }
        item {
            SpecialInfoItem(
                releaseDate = detailedModel.releaseDate,
                productionCountry = detailedModel.productionCountry,
                spokenLanguages = detailedModel.spokenLanguages,
                releaseStatus = detailedModel.releaseStatus,
                budget = formatNumber(detailedModel.budget),
                revenue = formatNumber(detailedModel.revenue),
                voteAverage = detailedModel.voteAverage
            )
        }
        item {
            RecommendationContentItem(
                model = recommendedModel
            )
        }
    }
}

@SuppressLint("DefaultLocale")
private fun formatNumber(number: Int): String {
    return "$ ${String.format("%,d", number)}"
}