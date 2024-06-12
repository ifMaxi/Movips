package com.maxidev.movips.detail.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import com.maxidev.movips.core.presentation.components.ItemCoil
import com.maxidev.movips.core.presentation.components.SectionItem
import com.maxidev.movips.core.presentation.ui.theme.oswald
import com.maxidev.movips.detail.domain.models.CreditsMovie

@Composable
fun CreditContentItem(
    modifier: Modifier = Modifier,
    model: LazyPagingItems<CreditsMovie>
) {
    val lazyState = rememberLazyGridState()
    val rememberModel = remember(model) { model }

    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        SectionItem(
            title = "Main Cast",
            fontSize = 24.sp,
            modifier = Modifier
                .padding(12.dp)
        )
        LazyHorizontalGrid(
            modifier = modifier
                .wrapContentHeight(Alignment.Top)
                .fillMaxWidth()
                .height(300.dp),
            state = lazyState,
            rows = GridCells.Fixed(2),
            contentPadding = PaddingValues(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(
                count = rememberModel.itemCount,
                key = rememberModel.itemKey { it.id }
            ) { data ->
                rememberModel[data]?.let {
                    CastingItem(
                        name = it.name.toString(),
                        photo = it.profilePath.toString(),
                        character = it.character.toString()
                    )
                }
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
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
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
                modifier = Modifier
                    .size(width = 86.dp, height = 120.dp)
            )
        }
        Column(
            modifier = Modifier
                .padding(4.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = name,
                fontFamily = oswald,
                fontSize = 18.sp
            )
            Text(
                text = "($character)",
                fontSize = 12.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier
                    .width(170.dp)
            )
        }
    }
}