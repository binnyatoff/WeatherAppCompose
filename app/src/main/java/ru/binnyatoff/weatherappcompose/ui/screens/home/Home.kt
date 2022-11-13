package ru.binnyatoff.weatherappcompose.ui.screens.home

import android.Manifest
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import ru.binnyatoff.weatherappcompose.ui.screens.components.WeatherScreen
import ru.binnyatoff.weatherappcompose.ui.theme.AppTheme

@Composable
fun Home(viewModel: HomeViewModel) {
    val viewState = viewModel.viewState.observeAsState()
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Card(
            shape = CircleShape,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 35.dp),
            backgroundColor = AppTheme.colors.secondary
        ) {
            Text(
                modifier = Modifier.padding(12.dp),
                text = "Current Weather",
                fontSize = 35.sp
            )
        }
        when (val state = viewState.value) {
            is HomeState.ValidatePermissions -> ValidatePermissions(viewModel = viewModel)
            is HomeState.Loading -> HomeLoading()
            is HomeState.Loaded -> HomeLoaded(state)
            is HomeState.Empty -> HomeEmpty()
            is HomeState.Error -> HomeError(error = state.error)
            else -> {
                HomeError(error = "ViewState Is Empty -_-")
            }
        }
    }
}

@Composable
fun HomeLoading() {
    WeatherScreen()
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ValidatePermissions(viewModel: HomeViewModel) {
    val listPermission = listOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    )

    val locationPermissionsState = rememberMultiplePermissionsState(
        permissions = listPermission
    )

    val permissionRevoked =
        locationPermissionsState.permissions[0].hasPermission or
                locationPermissionsState.permissions[1].hasPermission

    if (permissionRevoked) {
        viewModel.obtainEvent(HomeEvent.GpsPermissionGranted)
    } else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(onClick = {
                    locationPermissionsState.launchMultiplePermissionRequest()
                }) {
                    Text("Request permission")
                }
            }

        }
    }
}

@Composable
fun HomeLoaded(state: HomeState.Loaded) {
    with(state.currentWeather) {
        WeatherScreen(
            modifier = Modifier,
            icon = icon,
            temp = temp,
            humidity = humidity,
            windSpeed = windSpeed,
            location = location,
            currentTime = currentTime,
            loading = false
        )
    }
}

@Composable
fun HomeError(error: String) {
    Box(
        modifier = Modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(text = error)
    }

}

@Composable
fun HomeEmpty() {
    Text(text = "Empty")
}