package ru.binnyatoff.weatherappcompose.ui.screens.daily

import ru.binnyatoff.weatherappcompose.data.DailyMap

open class DailyState {
    object Loading : DailyState()

    data class Loaded(
        val listDaily: List<DailyMap>
    ) : DailyState()

    object Empty : DailyState()
    data class Error(val error: String) : DailyState()

}

sealed class DailyEvent() {
    object ScreenInit : DailyEvent()
}