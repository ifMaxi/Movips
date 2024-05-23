package com.maxidev.movips.core.navigation

sealed class Destinations(val route: String) {
    data object MovieDiscoverScreen : Destinations("movie_discover_screen")
    data object DetailScreen : Destinations("detail_screen")
}