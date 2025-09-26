package com.app.design_system.typography

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle

data class WusalTextStyle(
    val regular: TextStyle,
    val medium: TextStyle,
    val bold: TextStyle
)

internal val LocalWusalTextStyle = staticCompositionLocalOf { DefaultTextStyle }