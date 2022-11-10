package ru.binnyatoff.weatherappcompose.ui.screens.search

open class SearchState {
    object Loading : SearchState()

    class Loaded(

    ) : SearchState()

    object Empty : SearchState()
    data class Error(val error: String) : SearchState()

}