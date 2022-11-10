package ru.binnyatoff.weatherappcompose.ui.screens.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor() : ViewModel() {
    private val _viewState = MutableLiveData<SearchState>(SearchState.Empty)
    val viewState: LiveData<SearchState> = _viewState
}