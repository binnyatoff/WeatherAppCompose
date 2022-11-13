package ru.binnyatoff.weatherappcompose.data

import retrofit2.Response
import ru.binnyatoff.weatherappcompose.data.models.Coordinates
import ru.binnyatoff.weatherappcompose.data.modelsDTO.CurrentWeatherDTO
import ru.binnyatoff.weatherappcompose.data.modelsDTO.Daily
import ru.binnyatoff.weatherappcompose.data.modelsDTO.DailyWeatherDTO
import javax.inject.Inject

class Repository @Inject constructor(private val api: Api) {
    suspend fun getCurrentWeather(coordinates: Coordinates): Response<CurrentWeatherDTO> {
        val map = mapOf(
            "lat" to (coordinates.latitude),
            "lon" to (coordinates.longitude)
        )
        return api.getCurrentWeather(map)
    }

    suspend fun getDailyWeather(coordinates: Coordinates): Response<DailyWeatherDTO> {
        val map = mapOf(
            "lat" to (coordinates.latitude),
            "lon" to (coordinates.longitude)
        )
        return api.getWeatherDaily(map)
    }

    suspend fun getCityWeather(city: String): Response<CurrentWeatherDTO> {
        return api.getCityWeather(city = city)
    }
}

