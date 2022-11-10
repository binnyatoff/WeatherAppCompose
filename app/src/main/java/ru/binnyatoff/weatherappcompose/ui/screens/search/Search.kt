package ru.binnyatoff.weatherappcompose.ui.screens.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import ru.binnyatoff.weatherappcompose.ui.navigation.BottomNavigationItems

@Composable
fun Search(viewModel: SearchViewModel){
    val viewState = viewModel.viewState.observeAsState()
    val state = viewState.value

}