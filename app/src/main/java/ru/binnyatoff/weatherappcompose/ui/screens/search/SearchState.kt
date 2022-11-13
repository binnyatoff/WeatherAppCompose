package ru.binnyatoff.weatherappcompose.ui.screens.search

import ru.binnyatoff.weatherappcompose.data.models.CurrentWeather

/*
open class SearchState {
    object Loading : SearchState()

    data class Loaded(
        val currentWeather: CurrentWeather
    ) : SearchState()

    data class Empty(val searchText: String) : SearchState()
    data class Error(val error: String) : SearchState()

}
*/
data class SearchState(
    val currentWeather: CurrentWeather? = null,
    val searchText: String = "",
    val loading: Boolean = false
)