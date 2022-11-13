package ru.binnyatoff.weatherappcompose.ui.screens.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.binnyatoff.weatherappcompose.data.models.CurrentWeather
import ru.binnyatoff.weatherappcompose.ui.screens.components.SearchTextField
import ru.binnyatoff.weatherappcompose.ui.screens.components.WeatherScreen

@Composable
fun Search(viewModel: SearchViewModel) {
    val viewState = viewModel.viewState.observeAsState()
    val state = viewState.value
    if (state != null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            SearchTextField(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(15.dp),
                value = state.searchText,
                onValueChange = { searchText ->
                    viewModel.obtainEvent(SearchEvent.SearchTextChanged(searchText))
                },
                onClick = { viewModel.obtainEvent(SearchEvent.SearchWeather) }
            )

            if (state.currentWeather != null) {
                Loaded(currentWeather = state.currentWeather)
            }
        }
    }
}

@Composable
fun Loaded(currentWeather: CurrentWeather) {
    WeatherScreen(
        icon = currentWeather.icon,
        temp = currentWeather.temp,
        humidity = currentWeather.humidity,
        windSpeed = currentWeather.windSpeed,
        location = currentWeather.location,
        loading = false
    )

}