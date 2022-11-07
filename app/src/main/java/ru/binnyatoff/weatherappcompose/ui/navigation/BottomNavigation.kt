package ru.binnyatoff.weatherappcompose.ui.navigation

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.binnyatoff.weatherappcompose.R
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import ru.binnyatoff.weatherappcompose.ui.theme.AppTheme

enum class NavigationTree {
    Home, Search, Daily
}

sealed class BottomNavigationItems(var title: Int, var screen_route: String, var icon: Int) {
    object Home :
        BottomNavigationItems(
            R.string.home,
            NavigationTree.Home.name,
            R.drawable.ic_baseline_home_24
        )

    object Daily :
        BottomNavigationItems(
            R.string.daily,
            NavigationTree.Daily.name,
            R.drawable.ic_baseline_daily_24
        )

    object Search :
        BottomNavigationItems(
            R.string.search,
            NavigationTree.Search.name,
            R.drawable.ic_baseline_search_24
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

        BottomNavigationItem(modifier = Modifier,
            selectedContentColor = Color.Black,
            unselectedContentColor = Color.Gray,
            selected = currentRoute == BottomNavigationItems.Home.screen_route,
            label = { Text(text = stringResource(id = BottomNavigationItems.Home.title)) },

            onClick = { navController.navigate(NavigationTree.Home.name) },

            icon = {
                Icon(
                    painter = painterResource(id = BottomNavigationItems.Home.icon),
                    contentDescription = stringResource(
                        id = BottomNavigationItems.Home.title
                    )
                )
            })

        BottomNavigationItem(modifier = Modifier,
            selectedContentColor = Color.Black,
            unselectedContentColor = Color.Gray,
            selected = currentRoute == BottomNavigationItems.Daily.screen_route,
            label = { Text(text = stringResource(id = BottomNavigationItems.Daily.title)) },

            onClick = { navController.navigate(NavigationTree.Daily.name) },

            icon = {
                Icon(
                    painter = painterResource(id = BottomNavigationItems.Daily.icon),
                    contentDescription = stringResource(
                        id = BottomNavigationItems.Daily.title
                    )
                )
            })

        BottomNavigationItem(modifier = Modifier,
            selectedContentColor = Color.Black,
            unselectedContentColor = Color.Gray,
            selected = currentRoute == BottomNavigationItems.Search.screen_route,
            label = { Text(text = stringResource(id = BottomNavigationItems.Search.title)) },

            onClick = { navController.navigate(NavigationTree.Search.name) },

            icon = {
                Icon(
                    painter = painterResource(id = BottomNavigationItems.Search.icon),
                    contentDescription = stringResource(
                        id = BottomNavigationItems.Search.title
                    )
                )
            })
    }
}