package ru.binnyatoff.weatherappcompose.ui.screens.home

import ru.binnyatoff.weatherappcompose.data.models.CurrentWeather


open class HomeState {
    object ValidatePermissions : HomeState()
    object Loading : HomeState()

    data class Loaded(
        val currentWeather: CurrentWeather
    ) : HomeState()

    object Empty : HomeState()
    data class Error(val error: String) : HomeState()
}

sealed class HomeEvent() {
    object GpsPermissionGranted : HomeEvent()
}