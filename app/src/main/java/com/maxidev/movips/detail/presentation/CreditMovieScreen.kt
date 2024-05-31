package com.maxidev.movips.detail.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.maxidev.movips.R
import com.maxidev.movips.core.presentation.components.ItemCoil
import com.maxidev.movips.core.presentation.components.SectionItem
import com.maxidev.movips.core.presentation.components.StatusItem
import com.maxidev.movips.detail.domain.models.CreditsMovie
import com.maxidev.movips.detail.presentation.state.CreditsState

@Composable
fun CreditMovieScreen(
    viewModel: CreditsMovieViewModel = hiltViewModel(),
    movieId: Int
) {
    val state by viewModel.creditsState.collectAsStateWithLifecycle()

    LaunchedEffect(Int) {
        viewModel.fetchedCredits(movieId)
    }

    CreditsStatus(state = state)
}

@Composable
private fun CreditsStatus(state: CreditsState) {

    when (state) {
        is CreditsState.Success -> {
            CreditContent(model = state.onSuccess)
        }
        is CreditsState.Error -> { StatusItem(animation = R.raw.image_error) }
    }
}

@Composable
private fun CreditContent(
    modifier: Modifier = Modifier,
    model: List<CreditsMovie>
) {
    val lazyGridState = rememberLazyStaggeredGridState()
    val rememberModel = remember(model) { model }


    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(WindowInsets.statusBars.asPaddingValues()),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SectionItem(
            title = "Main Cast",
            fontSize = 34.sp,
            modifier = Modifier
                .padding(start = 10.dp)
        )

        LazyVerticalStaggeredGrid(
            modifier = modifier,
            columns = StaggeredGridCells.Adaptive(200.dp),
            state = lazyGridState,
            contentPadding = PaddingValues(10.dp),
            verticalItemSpacing = 10.dp,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(
                items = rememberModel,
                key = { it.id },
                contentType = { it.id }
            ) { data ->
                CastingItem(
                    name = data.name.toString(),
                    photo = data.profilePath.toString(),
                    character = data.character.toString()
                )
            }
        }
    }
}

@Composable
private fun CastingItem(
    modifier: Modifier = Modifier,
    name: String,
    photo: String,
    character: String
) {
    Column(
        modifier = modifier
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ElevatedCard(
            modifier = Modifier
                .wrapContentSize(),
            elevation = CardDefaults.elevatedCardElevation(8.dp),
            shape = RoundedCornerShape(10)
        ) {
            ItemCoil(
                image = photo,
                contentScale = ContentScale.Crop,
            )
        }
        Text(
            text = name
        )
        Text(
            text = "($character)"
        )
    }
}