package ru.binnyatoff.weatherappcompose.ui.screens.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.binnyatoff.weatherappcompose.data.Repository
import ru.binnyatoff.weatherappcompose.data.toCurrentWeather
import javax.inject.Inject

sealed class SearchEvent {
    object SearchWeather : SearchEvent()
    data class SearchTextChanged(val text: String) : SearchEvent()
}

@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _viewState = MutableLiveData(SearchState())
    val viewState: LiveData<SearchState> = _viewState

    fun obtainEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.SearchWeather -> searchWeather()
            is SearchEvent.SearchTextChanged -> searchTextChanged(event.text)
        }
    }

    private fun searchTextChanged(searchText: String) {
        _viewState.postValue(_viewState.value?.copy(searchText = searchText))
    }

    private fun searchWeather() {
        _viewState.postValue(_viewState.value?.copy(loading = true))
        viewModelScope.launch {
            try {
                val cityName = _viewState.value?.searchText ?: "Moscow"
                val response = repository.getCityWeather(cityName)
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        _viewState.postValue(
                            _viewState.value?.copy(currentWeather = body.toCurrentWeather())
                        )
                    }
                } else {
                    _viewState.postValue(_viewState.value?.copy(currentWeather = null))
                }
            } catch (e: Exception) {
                _viewState.postValue(_viewState.value?.copy(currentWeather = null))
            }
        }
    }
}