package ru.binnyatoff.weatherappcompose.ui.screens.daily

open class DailyState {
    object Loading : DailyState()

    data class Loaded(
        val weathers: List<Daily> //Rename this value
    ) : DailyState()

    object Empty : DailyState()
    data class Error(val error: String) : DailyState()

}