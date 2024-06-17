package com.maxidev.movips.core.navigation

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsIgnoringVisibility
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.maxidev.movips.detail.presentation.DetailMovieScreen
import com.maxidev.movips.movies.presentation.MoviesScreen
import com.maxidev.movips.movies.presentation.MoviesViewModel
import com.maxidev.movips.search.presentation.SearchMovieScreen
import com.maxidev.movips.trending.presentation.TrendingMoviesScreen

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        contentWindowInsets = WindowInsets.navigationBarsIgnoringVisibility,
        bottomBar = {
            NavigationBar(
                modifier = modifier
                    .fillMaxWidth(),
                tonalElevation = NavigationBarDefaults.Elevation
            ) {
                NavigationItemUtils.destinationList.forEach { screen ->
                    NavigationBarItem(
                        label = { Text(text = screen.title) },
                        selected = currentDestination?.hierarchy?.any {
                            it.route == screen.route
                        } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = screen.iconSelected,
                                contentDescription = screen.title
                            )
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            modifier = Modifier.padding(innerPadding),
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

                DetailMovieScreen(movieId = backStack ?: 0)
            }
            composable(route = Destinations.SearchScreen.route) {
                SearchMovieScreen(
                    onClick = {
                        navController.navigate(
                            Destinations.DetailScreen.route + "?movieId=${it}"
                        )
                    }
                )
            }
            composable(route = Destinations.TrendingScreen.route) {
                TrendingMoviesScreen(
                    onClick = {
                        navController.navigate(
                            Destinations.DetailScreen.route + "?movieId=${it}"
                        )
                    }
                )
            }
        }
    }
}