package ru.binnyatoff.weatherappcompose.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap
import ru.binnyatoff.weatherappcompose.data.modelsDTO.CurrentWeatherDTO
import ru.binnyatoff.weatherappcompose.data.modelsDTO.DailyWeatherDTO

interface Api {
    @GET("data/2.5/weather?units=metric")
    suspend fun getCurrentWeather(@QueryMap coordinates: Map<String, Double>): Response<CurrentWeatherDTO>

    @GET("data/2.5/onecall?units=metric&exclude=current,minutely,hourly,alerts")
    suspend fun getWeatherDaily(@QueryMap coordinates: Map<String, Double>) : Response<DailyWeatherDTO>

    @GET("data/2.5/weather?units=metric")
    suspend fun getCityWeather(@Query("q") city:String): Response<CurrentWeatherDTO>
}