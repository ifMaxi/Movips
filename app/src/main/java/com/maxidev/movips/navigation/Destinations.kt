package com.maxidev.movips.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.filled.Details
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Destinations(
    val route: String,
    val title: String,
    val iconSelected: ImageVector
) {
    data object MovieDiscoverScreen: Destinations(
        route = "movie_discover_screen",
        title = "Home",
        iconSelected = Icons.Filled.Home
    )

    data object DetailScreen: Destinations(
        route = "detail_screen",
        title = "Details",
        iconSelected = Icons.Filled.Details
    )

    data object SearchScreen: Destinations(
        route = "search_screen",
        title = "Search",
        iconSelected = Icons.Filled.Search
    )

    data object TrendingScreen: Destinations(
        route = "trending_screen",
        title = "Trending",
        iconSelected = Icons.AutoMirrored.Filled.TrendingUp
    )
}