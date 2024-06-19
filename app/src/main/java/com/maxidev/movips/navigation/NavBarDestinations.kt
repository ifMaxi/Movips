package com.maxidev.movips.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavBarDestinations(
    val route: NavDestinations,
    val title: String,
    val iconSelected: ImageVector
) {
    data object MovieDiscoverScreen: NavBarDestinations(
        route = NavDestinations.Movies,
        title = "Home",
        iconSelected = Icons.Filled.Home
    )
    data object SearchScreen: NavBarDestinations(
        route = NavDestinations.Search,
        title = "Search",
        iconSelected = Icons.Filled.Search
    )
    data object TrendingScreen: NavBarDestinations(
        route = NavDestinations.Trending,
        title = "Trending",
        iconSelected = Icons.AutoMirrored.Filled.TrendingUp
    )

    companion object {

        val destinationList = listOf(
            MovieDiscoverScreen,
            TrendingScreen,
            SearchScreen
        )
    }
}