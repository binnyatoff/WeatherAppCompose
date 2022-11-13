package ru.binnyatoff.weatherappcompose.ui.screens.daily

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.binnyatoff.weatherappcompose.data.DailyMap
import ru.binnyatoff.weatherappcompose.ui.screens.components.DailyWeatherCard
import ru.binnyatoff.weatherappcompose.ui.theme.AppTheme

@Composable
fun Daily(viewModel: DailyViewModel) {
    val viewState = viewModel.viewState.observeAsState()
    when (val state = viewState.value) {
        is DailyState.Loading -> DailyLoading()
        is DailyState.Loaded -> DailyLoaded(list = state.listDaily)
    }
}

@Composable
fun DailyLoading() {
    Text(text = "Loading")
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DailyLoaded(list: List<DailyMap>) {
    LazyColumn() {
        stickyHeader {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(AppTheme.colors.primary),
                contentAlignment = Alignment.Center
            ) {
                Card(
                    shape = CircleShape,
                    modifier = Modifier
                        .padding(top = 15.dp, bottom = 15.dp)
                        .align(Alignment.Center),
                    backgroundColor = AppTheme.colors.secondary
                ) {
                    Text(
                        modifier = Modifier.padding(12.dp),
                        text = "Daily Weather",
                        fontSize = 35.sp
                    )
                }
            }
        }
        items(list) { day ->
            Text(text = "Day ${day.dt}", Modifier.padding(start = 20.dp))
            DailyWeatherCard(
                humidity = day.humidity,
                pressure = day.pressure,
                temp = day.temp,
                icon = day.icon
            )
        }
    }

}