package com.maxidev.movips.presentation.navigation

sealed class Destinations(val route: String) {
    data object MovieDiscoverScreen : Destinations("movie_discover_screen")
    data object DetailScreen : Destinations("detail_screen")
}