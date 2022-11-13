package ru.binnyatoff.weatherappcompose.ui.screens.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import ru.binnyatoff.weatherappcompose.R

@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    value: String,
    onValueChange: (String) -> Unit
) {
    Row(modifier = modifier) {
        TextField(
            value = value, onValueChange = onValueChange
        )
        IconButton(onClick = onClick) {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = stringResource(
                    id = R.string.search
                )
            )
        }
    }
}