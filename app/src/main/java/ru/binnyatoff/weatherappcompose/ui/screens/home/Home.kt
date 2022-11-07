package ru.binnyatoff.weatherappcompose.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.binnyatoff.weatherappcompose.ui.theme.WeatherAppComposeTheme


@Composable
fun Home() {
    Box(
        modifier = Modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Location")
            Text(text = "Temperature ⁰С", fontSize = 70.sp)
            Row(modifier = Modifier) {
                Column() {
                    Text(text = "Humidity")
                }
                Spacer(modifier = Modifier.width(60.dp))
                Column() {
                    Text(text = "Wind")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WeatherAppComposeTheme {
        Home()
    }
}