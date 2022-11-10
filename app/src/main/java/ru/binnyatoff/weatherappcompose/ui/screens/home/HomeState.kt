package ru.binnyatoff.weatherappcompose.ui.screens.home


open class HomeState {
    object Loading:HomeState()

    data class Loaded(
        val icon: String,
        val temp: Double,
        val humidity: Int,
        val wind: Double,
        val location: String,
        val currentTime: Int
    ) : HomeState()

    object Empty : HomeState()
    data class Error(val error: String) : HomeState()

}