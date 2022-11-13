package ru.binnyatoff.weatherappcompose.ui.screens.daily

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.binnyatoff.weatherappcompose.data.GPS
import ru.binnyatoff.weatherappcompose.data.Repository
import ru.binnyatoff.weatherappcompose.data.models.Coordinates
import ru.binnyatoff.weatherappcompose.data.toDailyMap
import ru.binnyatoff.weatherappcompose.ui.screens.home.HomeViewModel
import javax.inject.Inject

@HiltViewModel
class DailyViewModel @Inject constructor(private val gps: GPS, private val repository: Repository) :
    ViewModel() {
    private val _viewState = MutableLiveData<DailyState>(DailyState.Loading)
    val viewState: LiveData<DailyState> = _viewState

    fun obtainEvent(event: DailyEvent) {
        when (event) {
            is DailyEvent.ScreenInit -> getCoordinates()
        }
    }

    private fun getCoordinates(){
        viewModelScope.launch(Dispatchers.IO){
            val gpsLocation = gps.location
            gpsLocation.collect { coordinates ->
                Log.e(HomeViewModel.TAG, coordinates.toString())
                getDailyWeatherList(coordinates)
            }
        }

    }

    private fun getDailyWeatherList(coordinates: Coordinates) {
        viewModelScope.launch {
            try {
                val response = repository.getDailyWeather(coordinates)
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        _viewState.postValue(
                            DailyState.Loaded(
                                body.toDailyMap()

                            )
                        )
                    } else {
                        _viewState.postValue(
                            DailyState.Empty
                        )
                        Log.e(HomeViewModel.TAG, response.toString())
                    }
                }
            } catch (e: Exception) {
                _viewState.postValue(DailyState.Error(e.toString()))
                Log.e(HomeViewModel.TAG, e.toString())
            }
        }

    }
}