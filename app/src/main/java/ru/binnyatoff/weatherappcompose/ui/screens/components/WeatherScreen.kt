package ru.binnyatoff.weatherappcompose.ui.screens.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import ru.binnyatoff.weatherappcompose.R
import ru.binnyatoff.weatherappcompose.data.toWeatherIcon
import ru.binnyatoff.weatherappcompose.ui.placeholderMain
import ru.binnyatoff.weatherappcompose.ui.theme.AppTheme

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun WeatherScreen(
    modifier: Modifier = Modifier,
    icon: String = "04n".toWeatherIcon(),
    temp: Double = 0.0,
    humidity: Int = 0,
    windSpeed: Double = 0.0,
    location: String = "No Location",
    currentTime: Int = 0,
    loading: Boolean = true
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        backgroundColor = AppTheme.colors.secondary
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = location, fontSize = 35.sp, modifier = Modifier.placeholderMain(loading)
            )
            GlideImage(
                model = icon,
                modifier = Modifier
                    .size(75.dp)
                    .placeholderMain(loading),
                contentDescription = stringResource(id = R.string.weatherIcon)
            )
            Text(
                text = "$temp ⁰С",
                fontSize = 50.sp,
                modifier = Modifier.placeholderMain(loading)
            )
            Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically) {
                TextWithIcon(icon = R.drawable.ic_humidity, text = "$humidity %", loading = loading)
                Spacer(modifier = Modifier.width(60.dp))
                TextWithIcon(icon = R.drawable.ic_wind, text = "$windSpeed km/h", loading = loading)
            }

        }
    }
}