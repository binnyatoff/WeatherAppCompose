package ru.binnyatoff.weatherappcompose.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import ru.binnyatoff.weatherappcompose.R
import ru.binnyatoff.weatherappcompose.ui.theme.AppTheme
import ru.binnyatoff.weatherappcompose.ui.theme.WeatherAppComposeTheme


@Composable
fun Home(viewModel: HomeViewModel) {
    val viewState = viewModel.viewState.observeAsState()
    when (val state = viewState.value) {
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

@Composable
fun HomeLoading() {
    Text(text = "Loading")
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun HomeLoaded(
    state: HomeState.Loaded
) {
    val icon = "http://openweathermap.org/img/wn/${state.icon}@2x.png"
    with(state) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = AppTheme.colors.primary),
            contentAlignment = Alignment.Center
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
                        Text(text = "$wind km/h", fontSize = 25.sp)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeLoadedPreview() {
    WeatherAppComposeTheme {
        HomeLoaded(
            state = HomeState.Loaded(
                icon = "null",
                temp = 12.0,
                humidity = 10,
                wind = 12.0,
                location = "Moscow",
                currentTime = 12
            )
        )

    }
}