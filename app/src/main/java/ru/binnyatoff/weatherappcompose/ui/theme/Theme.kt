package ru.binnyatoff.weatherappcompose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class Colors(
    val primary:Color,
    val secondary: Color,
    val systemBarColor: Color,
    val darkIcons: Boolean
)

private val LightColorPalette = Colors(
    primary = BlizzardBlue,
    secondary = Water,
    systemBarColor = Color.White,
    darkIcons = true
)

private val DarkColorPalette = Colors(
    primary = YankeesBlue,
    secondary = Charcoal,
    systemBarColor = Color.Black,
    darkIcons = false
)

@Composable
fun WeatherAppComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    CompositionLocalProvider(
        LocalColorProvider provides colors,
        content = content
    )
}

object AppTheme{
    val colors: Colors
        @Composable
        @ReadOnlyComposable
        get() = LocalColorProvider.current
}

val LocalColorProvider = staticCompositionLocalOf<Colors> {
    error("No default colors provider ")
}