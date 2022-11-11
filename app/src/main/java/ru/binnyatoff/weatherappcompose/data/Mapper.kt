package ru.binnyatoff.weatherappcompose.data

import ru.binnyatoff.weatherappcompose.data.models.CurrentWeather
import ru.binnyatoff.weatherappcompose.data.modelsDTO.CurrentWeatherDTO

fun CurrentWeatherDTO.toCurrentWeather(): CurrentWeather {
    val currentWeatherDTO = this
    return CurrentWeather(
        icon = currentWeatherDTO.weather[0].icon,
        temp = currentWeatherDTO.main.temp,
        humidity = currentWeatherDTO.main.humidity,
        windSpeed = wind.speed,
        location = name,
        currentTime = timezone,
    )
}

