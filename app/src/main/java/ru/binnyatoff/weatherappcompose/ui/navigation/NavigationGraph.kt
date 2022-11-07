package ru.binnyatoff.weatherappcompose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.binnyatoff.weatherappcompose.ui.screens.daily.Daily
import ru.binnyatoff.weatherappcompose.ui.screens.home.Home
import ru.binnyatoff.weatherappcompose.ui.screens.search.Search

@Composable
fun NavigationGraph(navController: NavHostController, modifier: Modifier) {
    NavHost(navController = navController, startDestination = NavigationTree.Home.name, modifier = modifier) {

        composable(NavigationTree.Home.name) {
            Home()
        }

        composable(NavigationTree.Daily.name) {
            Daily()
        }

        composable(NavigationTree.Search.name) {
            Search()
        }
    }
}