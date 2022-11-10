package ru.binnyatoff.weatherappcompose.data

import retrofit2.Response
import ru.binnyatoff.weatherappcompose.data.models.CurrentWeather
import javax.inject.Inject

class Repository @Inject constructor(private val api: Api) {
    suspend fun getCurrentWeather(): Response<CurrentWeather> {
        return api.getCityWeather("Moscow")
    }
}