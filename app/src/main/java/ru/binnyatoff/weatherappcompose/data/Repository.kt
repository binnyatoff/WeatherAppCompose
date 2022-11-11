package ru.binnyatoff.weatherappcompose.data

import retrofit2.Response
import ru.binnyatoff.weatherappcompose.data.models.Coordinates
import ru.binnyatoff.weatherappcompose.data.modelsDTO.CurrentWeatherDTO
import javax.inject.Inject

class Repository @Inject constructor(private val api: Api) {
    suspend fun getCurrentWeather(coordinates: Coordinates): Response<CurrentWeatherDTO> {
        val map = mapOf(
            "lat" to (coordinates.latitude),
            "lon" to (coordinates.longitude)
        )
        return api.getCurrentWeather(map)
    }
}