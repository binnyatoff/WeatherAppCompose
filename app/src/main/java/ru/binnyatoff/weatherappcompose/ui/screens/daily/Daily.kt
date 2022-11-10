package ru.binnyatoff.weatherappcompose.ui.screens.daily

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import ru.binnyatoff.weatherappcompose.R
import ru.binnyatoff.weatherappcompose.ui.theme.WeatherAppComposeTheme
import java.time.LocalDate
import java.util.*

data class Daily(
    val date: LocalDate,
    val temp: String,
    val icon: String
)

@Composable
fun Daily(viewModel: DailyViewModel) {
    val viewState = viewModel.viewState.observeAsState()
    when (val state = viewState.value) {
        is DailyState.Loaded -> DailyLoaded(state = state)
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalGlideComposeApi::class)
@Composable
fun DailyLoaded(state: DailyState.Loaded) {
    val list = state.weathers
    LazyColumn() {
        stickyHeader {
            Text(text = "Daily Weather")
        }
        items(list) { day ->
            Card(){
                Row() {
                    Text(text = day.date.toString())
                    Text(text = day.temp)
                    GlideImage(
                        model = day.icon,
                        contentDescription = stringResource(id = R.string.weatherIcon)
                    )
                }
            }

        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun DailyLoadedPreview() {
    WeatherAppComposeTheme {
        val day: Daily = Daily(
            date = LocalDate.parse(
                "2018-12-12"
            ),
            temp = "12",
            icon = "Not Found"
        )
        val list: List<Daily> = listOf(day)
        DailyLoaded(
            state = DailyState.Loaded(
                list
            )
        )
    }
}