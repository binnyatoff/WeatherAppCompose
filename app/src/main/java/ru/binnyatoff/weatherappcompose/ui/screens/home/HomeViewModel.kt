package ru.binnyatoff.weatherappcompose.ui.screens.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.binnyatoff.weatherappcompose.GPS
import ru.binnyatoff.weatherappcompose.data.Repository
import ru.binnyatoff.weatherappcompose.data.models.Coordinates
import ru.binnyatoff.weatherappcompose.data.toCurrentWeather
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: Repository, private val gps: GPS) :
    ViewModel() {
    private val _viewState = MutableLiveData<HomeState>(HomeState.ValidatePermissions)
    val viewState: LiveData<HomeState> = _viewState

    fun obtainEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.GpsPermissionGranted -> {
                getLocate()
                _viewState.postValue(HomeState.Loading)
            }
        }
    }

    private fun getLocate() {
        gps.getLocate()
        viewModelScope.launch {
            val gpsLocation = gps.location
            gpsLocation.collect { coordinates ->
                getCurrentWeather(coordinates)
            }
        }
    }

    private fun getCurrentWeather(coordinates: Coordinates) {
        viewModelScope.launch {
            try {
                val response = repository.getCurrentWeather(coordinates)
                Log.e("TAG", response.toString())
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        _viewState.postValue(
                            HomeState.Loaded(
                                body.toCurrentWeather()
                            )
                        )
                    } else {
                        _viewState.postValue(
                            HomeState.Empty
                        )
                        Log.e(TAG, response.toString())
                    }
                }
            } catch (e: Exception) {
                _viewState.postValue(HomeState.Error(e.toString()))
                Log.e(TAG, e.toString())
            }
        }
    }

    companion object {
        const val TAG = "TAG"
    }
}