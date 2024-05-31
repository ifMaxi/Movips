package com.maxidev.movips.core.navigation

sealed class Destinations(val route: String) {
    data object MovieDiscoverScreen: Destinations("movie_discover_screen")
    data object DetailScreen: Destinations("detail_screen")
    data object ImageBackScreen: Destinations("image_back_screen")
    data object CreditsScreen: Destinations("credits_screen")
    data object SearchScreen: Destinations("search_screen")
}