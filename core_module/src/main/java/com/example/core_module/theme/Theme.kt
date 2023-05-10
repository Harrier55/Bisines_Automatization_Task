package com.example.core_module.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.*

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object ComposeMaterialTheme {


    private val DarkColorPalette = darkColorScheme(
        primary = Purple200,
        secondary = Teal200
    )

    private val LightColorPalette = lightColorScheme(
        primary = Purple500,
        secondary = Color.White,
        surface = Color.White,
        background = Color.LightGray,
        onSurface = Color.Red

    )

    @Composable
    fun ComposeMaterialTheme(
        darkTheme: Boolean = false,
        content: @Composable () -> Unit
    ) {
        val colors = if (darkTheme) {
            DarkColorPalette
        } else {
            LightColorPalette
        }

        MaterialTheme(
            colorScheme = colors,
            typography = MyTypography,
//            shapes = Shapes,
            content = content
        )
    }
}