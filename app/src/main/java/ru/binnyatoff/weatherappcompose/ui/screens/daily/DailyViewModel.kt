package ru.binnyatoff.weatherappcompose.ui.screens.daily

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DailyViewModel @Inject constructor() : ViewModel() {
    private val _viewState = MutableLiveData<DailyState>(DailyState.Loading)
    val viewState: LiveData<DailyState> = _viewState

    private suspend fun getDailyWeatherList() {

    }
}