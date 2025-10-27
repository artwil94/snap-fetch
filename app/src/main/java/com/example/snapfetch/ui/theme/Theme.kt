package com.example.snapfetch.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable

@Composable
fun SnapFetchTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

object SfTheme {
    val typography: SfTypography
        @Composable get() = SfTypography()
    val fonts: Fonts = Fonts()
    val dimensions: SfDimensions
        @Composable get() = SfDimensions()
    val colors: SfColors
        @Composable get() = SfColors()
    val shapes: SfShapes
        @Composable get() = SfShapes()
}