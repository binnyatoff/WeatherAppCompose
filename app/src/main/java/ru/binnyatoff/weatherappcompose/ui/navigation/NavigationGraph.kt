package ru.binnyatoff.weatherappcompose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.binnyatoff.weatherappcompose.ui.screens.daily.Daily
import ru.binnyatoff.weatherappcompose.ui.screens.daily.DailyViewModel
import ru.binnyatoff.weatherappcompose.ui.screens.home.Home
import ru.binnyatoff.weatherappcompose.ui.screens.home.HomeViewModel
import ru.binnyatoff.weatherappcompose.ui.screens.search.Search
import ru.binnyatoff.weatherappcompose.ui.screens.search.SearchViewModel

@Composable
fun NavigationGraph(navController: NavHostController, modifier: Modifier) {
    NavHost(
        navController = navController,
        startDestination = NavigationTree.Home.name,
        modifier = modifier
    ) {

        composable(NavigationTree.Home.name) {
            val viewModel = hiltViewModel<HomeViewModel>()
            Home(viewModel = viewModel)
        }

        composable(NavigationTree.Daily.name) {
            val viewModel = hiltViewModel<DailyViewModel>()
            Daily(viewModel = viewModel)
        }

        composable(NavigationTree.Search.name) {
            val viewModel = hiltViewModel<SearchViewModel>()
            Search(viewModel = viewModel)
        }
    }
}