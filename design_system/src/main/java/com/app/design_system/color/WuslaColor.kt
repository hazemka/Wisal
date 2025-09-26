package com.app.design_system.color

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class WuslaColor(
    val background: Background,
    val shade: Shade,
    val brand: Color,
    val button: Button,
    val stroke: Color,
    val additional: Additional
)

data class Background(
    val defaultScreen: Color,
    val customScreen: Color,
    val card: Color,
    val bottomSheet: Color,
    val progressBar: Color,
    val iconBackground: Color,
    val postBackground: Color
)

data class Shade(
    val primary: Color,
    val secondary: Color,
    val tertiary: Color,
    val quaternary: Color,
)

data class Button(
    val primary: Color,
    val disabled: Color,
)

data class Additional(
    val iconColor: Color,
)

internal val LocaleWusalColors = staticCompositionLocalOf { darkThemeColor }

val LocalLanguage: ProvidableCompositionLocal<String> = staticCompositionLocalOf { "ar" }