package com.app.design_system.typography

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.app.design_system.R

val cairo = FontFamily(
    Font(R.font.cairo_regular, FontWeight.Normal),
    Font(R.font.cairo_medium, FontWeight.Medium),
    Font(R.font.cairo_bold, FontWeight.Bold)
)

internal val DefaultTextStyle = WusalTextStyle(
    regular = TextStyle(
        fontFamily = cairo,
        fontWeight = FontWeight.Normal
    ),
    medium = TextStyle(
        fontFamily = cairo,
        fontWeight = FontWeight.Medium
    ),
    bold = TextStyle(
        fontFamily = cairo,
        fontWeight = FontWeight.Bold
    )
)