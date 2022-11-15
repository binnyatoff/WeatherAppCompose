package ru.binnyatoff.weatherappcompose.ui.screens.daily

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.binnyatoff.weatherappcompose.R
import ru.binnyatoff.weatherappcompose.data.DailyMap
import ru.binnyatoff.weatherappcompose.data.modelsDTO.Temp
import ru.binnyatoff.weatherappcompose.ui.screens.components.DailyWeatherCard
import ru.binnyatoff.weatherappcompose.ui.theme.AppTheme

@Composable
fun Daily(viewModel: DailyViewModel) {
    val viewState = viewModel.viewState.observeAsState()
    when (val state = viewState.value) {
        is DailyState.Loading -> DailyLoading()
        is DailyState.Loaded -> DailyLoaded(list = state.listDaily, false)
        is DailyState.Empty -> DailyEmpty()
        is DailyState.Error -> DailyError(state.error)
    }
    LaunchedEffect(key1 = Unit, block = {
        viewModel.obtainEvent(DailyEvent.ScreenInit)
    })
}

@Composable
fun DailyError(error: String) {
    Text(text = error)
}

@Composable
fun DailyEmpty() {
    Text(text = stringResource(id = R.string.viewEmpty))
}

@Composable
fun DailyLoading() {
    val temp = Temp(
        day = 0.0,
        eve = 0.0,
        max = 0.0,
        min = 0.0,
        morn = 0.0,
        night = 0.0
    )
    val dailyMap = DailyMap(
        dt = "10/10/10",
        humidity = 0,
        pressure = 0,
        temp = temp,
        icon = "04n"
    )
    val list = List(7){dailyMap}
    DailyLoaded(list = list, loading = true)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DailyLoaded(
    list: List<DailyMap>,
    loading: Boolean
) {
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
                        text = stringResource(id = R.string.dailyWeather),
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
                icon = day.icon,
                loading = loading
            )
        }
    }

}