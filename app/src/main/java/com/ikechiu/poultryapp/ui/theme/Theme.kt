package com.ikechiu.poultryapp.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color


private val DarkColorPalette = darkColors(
    primary = PrimaryNight,
    primaryVariant = PrimaryDark,
    secondary = AccentDark,
    background = Color(0xFFD7CCC8),
    onPrimary = Color(0xFFFFFFFF),
    surface = Color(0xFFEEEBE7),
    onSurface = Color.Black,
)

private val LightColorPalette = lightColors(
    primary = PrimaryDay,
    primaryVariant = PrimaryDark,
    secondary = AccentLight,

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun PoultryAppTheme(content: @Composable() () -> Unit) {
    val darkTheme = isSystemInDarkTheme() || PoultryAppThemeSettings.isDarkThemeEnabled
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

object PoultryAppThemeSettings {
    var isDarkThemeEnabled by mutableStateOf(false)
}