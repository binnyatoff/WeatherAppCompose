package ru.binnyatoff.weatherappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import ru.binnyatoff.weatherappcompose.ui.navigation.BottomNavigation
import ru.binnyatoff.weatherappcompose.ui.navigation.NavigationGraph
import ru.binnyatoff.weatherappcompose.ui.theme.AppTheme
import ru.binnyatoff.weatherappcompose.ui.theme.WeatherAppComposeTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = AppTheme.colors.primary
                ) {
                   MainScreenView()
                }
            }
        }
    }

}

@Composable
fun MainScreenView() {
    val systemUiController = rememberSystemUiController()
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = AppTheme.colors.primary,
        bottomBar = {
            BottomNavigation(navController = navController)
        }
    ) {paddingValues ->
        systemUiController.setSystemBarsColor(
            color = AppTheme.colors.systemBarColor,
            darkIcons = AppTheme.colors.darkIcons
        )
        NavigationGraph(navController = navController, modifier = Modifier.padding(paddingValues))
    }
}

