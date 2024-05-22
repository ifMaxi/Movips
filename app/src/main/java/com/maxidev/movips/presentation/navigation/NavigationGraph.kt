package com.maxidev.movips.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.maxidev.movips.presentation.detail.DetailMovieScreen
import com.maxidev.movips.presentation.movies.MoviesScreen
import com.maxidev.movips.presentation.movies.MoviesViewModel

@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Destinations.MovieDiscoverScreen.route
    ) {
        composable(route = Destinations.MovieDiscoverScreen.route) {
            val viewModel = hiltViewModel<MoviesViewModel>()

            MoviesScreen(
                viewmodel = viewModel,
                onClick = {
                    navController.navigate(
                        Destinations.DetailScreen.route + "?movieId=${it}"
                    )
                }
            )
        }
        composable(
            route = Destinations.DetailScreen.route + "?movieId={movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) { backStackEntry ->
            val backStack = backStackEntry.arguments?.getInt("movieId")

            DetailMovieScreen(
                movieId = backStack ?: 0
            )
        }
    }
}