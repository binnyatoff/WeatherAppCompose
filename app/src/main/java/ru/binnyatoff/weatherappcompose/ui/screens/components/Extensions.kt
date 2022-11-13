package ru.binnyatoff.weatherappcompose.ui.screens.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer

fun Modifier.placeholderMain(loading: Boolean): Modifier {
    return placeholder(
        visible = loading,
        shape = RoundedCornerShape(4.dp),
        color = Color.White,
        highlight = PlaceholderHighlight.shimmer(
            highlightColor = Color.Gray
        )
    )
}

@Composable
fun RowScope.BottomViewItem(
    modifier: Modifier,
    label: Int,
    onClick: () -> Unit,
    icon: Int,
    iconTitle: Int,
    selected: Boolean
) {
    BottomNavigationItem(
        modifier = modifier,
        selectedContentColor = Color.White,
        unselectedContentColor = Color.Black,
        label = { Text(text = stringResource(id = label)) },
        selected = selected,
        onClick = onClick,
        icon = {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = stringResource(
                    id = iconTitle
                )
            )
        }
    )
}