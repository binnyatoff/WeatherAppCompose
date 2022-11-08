package ru.binnyatoff.weatherappcompose.ui.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor():ViewModel() {
    private val _state = MutableLiveData<HomeState>(HomeState.Loading)
    val state: LiveData<HomeState> = _state

    private fun getWeather() {
        viewModelScope.launch {

        }
    }
}