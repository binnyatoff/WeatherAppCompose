package ru.binnyatoff.weatherappcompose.ui.screens.home

import android.Manifest
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import ru.binnyatoff.weatherappcompose.R
import ru.binnyatoff.weatherappcompose.data.models.CurrentWeather
import ru.binnyatoff.weatherappcompose.ui.theme.AppTheme
import ru.binnyatoff.weatherappcompose.ui.theme.WeatherAppComposeTheme

@Composable
fun Home(viewModel: HomeViewModel) {
    val viewState = viewModel.viewState.observeAsState()
    when (val state = viewState.value) {
        is HomeState.ValidatePermissions -> ValidatePermissions(viewModel = viewModel)
        is HomeState.Loading -> HomeLoading()
        is HomeState.Loaded -> HomeLoaded(state = state)
        is HomeState.Empty -> HomeEmpty()
        is HomeState.Error -> HomeError(error = state.error)
        else -> {
            HomeError(error = "ViewState Is Empty -_-")
        }
    }
}

@Composable
fun HomeLoading() {
    Text(text = "Loading")
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

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun HomeLoaded(state: HomeState.Loaded) {
    with(state.currentWeather) {
        val icon = "http://openweathermap.org/img/wn/${icon}@2x.png"
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = AppTheme.colors.primary),
            contentAlignment = Alignment.Center,
        ) {
            Card(
                shape = CircleShape,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 35.dp),

                ) {
                Text(
                    modifier = Modifier.padding(12.dp),
                    text = "Current Weather",
                    fontSize = 35.sp
                )
            }
            Card(
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = location, fontSize = 35.sp)
                    GlideImage(
                        model = icon,
                        modifier = Modifier.size(75.dp),
                        contentDescription = stringResource(id = R.string.weatherIcon)
                    )
                    Text(text = "$temp ⁰С", fontSize = 50.sp)
                    Row(modifier = Modifier) {
                        Column() {
                            Text(text = "Humidity", fontSize = 25.sp)
                            Text(text = "$humidity %", fontSize = 25.sp)
                        }
                        Spacer(modifier = Modifier.width(60.dp))
                        Column() {
                            Text(text = "Wind", fontSize = 25.sp)
                            Text(text = "$windSpeed km/h", fontSize = 25.sp)
                        }
                    }
                }
            }

        }
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

@Preview(showBackground = true)
@Composable
fun HomeLoadedPreview() {
    WeatherAppComposeTheme {
        HomeLoaded(
            state = HomeState.Loaded(
                currentWeather = CurrentWeather(
                    icon = "http://openweathermap.org/img/wn/4@2x.png",
                    temp = 12.0,
                    humidity = 10,
                    windSpeed = 12.0,
                    location = "Moscow",
                    currentTime = 12
                )
            )
        )

    }
}