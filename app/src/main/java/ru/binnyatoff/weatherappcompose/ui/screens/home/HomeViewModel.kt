package ru.binnyatoff.weatherappcompose.ui.screens.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.binnyatoff.weatherappcompose.data.Repository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _viewState = MutableLiveData<HomeState>(HomeState.Loading)
    val viewState: LiveData<HomeState> = _viewState

    init {
        getCurrentWeather()
    }

    private fun getCurrentWeather() {
        viewModelScope.launch {
            try {
                val response = repository.getCurrentWeather()
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        with(body) {
                            _viewState.postValue(
                                HomeState.Loaded(
                                    icon = weather[0].icon,
                                    temp = main.temp,
                                    humidity = main.humidity,
                                    wind = wind.speed,
                                    location = name,
                                    currentTime = timezone
                                )
                            )

                        }
                    }
                    Log.e(TAG, response.toString())


                }
            } catch (e: Exception) {
                Log.e(TAG, e.toString())
            }
        }
    }

    companion object {
        const val TAG = "TAG"
    }
}