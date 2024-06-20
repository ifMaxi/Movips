package com.maxidev.movips.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.maxidev.movips.presentation.detail.DetailMovieScreen
import com.maxidev.movips.presentation.detail.DetailedMovieViewModel
import com.maxidev.movips.presentation.movies.MoviesScreen
import com.maxidev.movips.presentation.movies.MoviesViewModel
import com.maxidev.movips.presentation.search.SearchMovieScreen
import com.maxidev.movips.presentation.search.SearchMovieViewModel
import com.maxidev.movips.presentation.trending.TrendingMovieViewModel
import com.maxidev.movips.presentation.trending.TrendingMoviesScreen
import kotlinx.serialization.Serializable

@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: NavDestinations = NavDestinations.Movies
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        contentWindowInsets = ScaffoldDefaults.contentWindowInsets,
        bottomBar = {
            NavigationBar(
                modifier = modifier
                    .fillMaxWidth(),
                tonalElevation = NavigationBarDefaults.Elevation,
                containerColor = MaterialTheme.colorScheme.background
            ) {
                NavBarDestinations.destinationList.forEach { screen ->
                    NavigationBarItem(
                        label = { Text(text = screen.title) },
                        selected = currentDestination?.hierarchy?.any {
                            //it.route == screen.route
                            it.route?.equals(screen.route) == true } == true,
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
            startDestination = startDestination
        ) {
            composable<NavDestinations.Movies> {
                val viewModel = hiltViewModel<MoviesViewModel>()

                MoviesScreen(
                    viewmodel = viewModel,
                    onClick = {
                        navController.navigate(NavDestinations.Detail(movieId = it))
                    }
                )
            }
            composable<NavDestinations.Detail> { backStackEntry ->
                val viewmodel = hiltViewModel<DetailedMovieViewModel>()
                val args = backStackEntry.toRoute<NavDestinations.Detail>().movieId

                DetailMovieScreen(
                    viewModel = viewmodel,
                    movieId = args
                )
            }
            composable<NavDestinations.Search> {
                val viewModel = hiltViewModel<SearchMovieViewModel>()

                SearchMovieScreen(
                    viewModel = viewModel,
                    onClick = {
                        navController.navigate(NavDestinations.Detail(movieId = it))
                    }
                )
            }
            composable<NavDestinations.Trending> {
                val viewModel = hiltViewModel<TrendingMovieViewModel>()

                TrendingMoviesScreen(
                    viewModel = viewModel,
                    onClick = {
                        navController.navigate(NavDestinations.Detail(movieId = it))
                    }
                )
            }
        }
    }
}

@Serializable
sealed class NavDestinations {
    @Serializable
    data object Movies: NavDestinations()
    @Serializable
    data object Search: NavDestinations()
    @Serializable
    data object Trending: NavDestinations()
    @Serializable
    data class Detail(val movieId: Int): NavDestinations()
}