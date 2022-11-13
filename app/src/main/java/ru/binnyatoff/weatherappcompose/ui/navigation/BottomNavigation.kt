package ru.binnyatoff.weatherappcompose.ui.navigation

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.binnyatoff.weatherappcompose.R
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import ru.binnyatoff.weatherappcompose.ui.screens.components.BottomViewItem
import ru.binnyatoff.weatherappcompose.ui.theme.AppTheme

enum class NavigationTree {
    Home, Search, Daily
}

sealed class BottomNavigationItems(var title: Int, var screen_route: String, var icon: Int) {
    object Home :
        BottomNavigationItems(
            R.string.home,
            NavigationTree.Home.name,
            R.drawable.ic_home
        )

    object Daily :
        BottomNavigationItems(
            R.string.daily,
            NavigationTree.Daily.name,
            R.drawable.ic_daily
        )

    object Search :
        BottomNavigationItems(
            R.string.search,
            NavigationTree.Search.name,
            R.drawable.ic_search
        )

}

@Composable
fun BottomNavigation(navController: NavController) {
    BottomNavigation(
        modifier = Modifier,
        backgroundColor = AppTheme.colors.secondary
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        BottomViewItem(
            modifier = Modifier,
            label = BottomNavigationItems.Home.title,
            onClick = {
                navController.navigate(NavigationTree.Home.name) {
                    navController.graph.startDestinationRoute?.let { screen_route ->
                        popUpTo(screen_route) {
                            saveState = true
                        }
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            icon = BottomNavigationItems.Home.icon,
            iconTitle = BottomNavigationItems.Home.title,
            selected = currentRoute == BottomNavigationItems.Home.screen_route
        )

        BottomViewItem(
            modifier = Modifier,
            label = BottomNavigationItems.Daily.title,
            onClick = {
                navController.navigate(NavigationTree.Daily.name) {
                    navController.graph.startDestinationRoute?.let { screen_route ->
                        popUpTo(screen_route) {
                            saveState = true
                        }
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            icon = BottomNavigationItems.Daily.icon,
            iconTitle = BottomNavigationItems.Daily.title,
            selected = currentRoute == BottomNavigationItems.Daily.screen_route
        )
        BottomViewItem(
            modifier = Modifier,
            label = BottomNavigationItems.Search.title,
            onClick = {
                navController.navigate(NavigationTree.Search.name)
                {
                    navController.graph.startDestinationRoute?.let { screen_route ->
                        popUpTo(screen_route) {
                            saveState = true
                        }
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            icon = BottomNavigationItems.Search.icon,
            iconTitle = BottomNavigationItems.Search.title,
            selected = currentRoute == BottomNavigationItems.Search.screen_route
        )
    }
}

