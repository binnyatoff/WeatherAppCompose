package ru.binnyatoff.weatherappcompose.ui.screens.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import ru.binnyatoff.weatherappcompose.R
import ru.binnyatoff.weatherappcompose.data.modelsDTO.Temp
import ru.binnyatoff.weatherappcompose.ui.theme.AppTheme


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DailyWeatherCard(
    humidity: Int,
    pressure: Int,
    temp: Temp,
    icon: String,
    loading: Boolean = false
) {

    Card(
        modifier = Modifier
            .padding(start = 15.dp, end = 15.dp, bottom = 15.dp)
            .fillMaxWidth()
            .placeholderMain(loading),
        backgroundColor = AppTheme.colors.secondary
    ) {
        Column() {
            Row() {
                Column(modifier = Modifier.padding(start = 5.dp, top = 5.dp, bottom = 5.dp)) {
                    Text(text = "Max: ${temp.max} ⁰С", fontSize = 20.sp)
                    Text(text = "Min: ${temp.min} ⁰С", fontSize = 20.sp)
                }
                Spacer(modifier = Modifier.weight(1F))
                GlideImage(
                    model = icon,
                    contentDescription = stringResource(id = R.string.weatherIcon),
                    modifier = Modifier.size(70.dp)
                )
                Spacer(modifier = Modifier.weight(1F))
                Column(modifier = Modifier.padding(end = 5.dp, top = 5.dp, bottom = 5.dp)) {
                    TextWithIcon(
                        icon = R.drawable.ic_humidity,
                        text = "$humidity %",
                        loading = loading,
                        contentDescription = R.string.humidity
                    )
                    TextWithIcon(
                        icon = R.drawable.ic_pressure,
                        text = "$pressure",
                        loading = loading,
                        contentDescription = R.string.pressure
                    )
                }
            }
        }
    }
}