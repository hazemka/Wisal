package com.app.design_system.color

import androidx.compose.ui.graphics.Color

val darkThemeColor = WuslaColor(
    background = Background(
        defaultScreen = Color(0xFFFFFFFF),
        customScreen = Color(0xFF8B5CF6),
        card = Color(0xFFFFFFFF),
        bottomSheet = Color(0xFFFFFFFF),
        progressBar = Color(0xFFE5E7EB),
        iconBackground = Color(0xFFDBEAFE),
        postBackground = Color(0xFFFFFFFF),
    ),
    shade = Shade(
        primary = Color(0xFF1F2937),
        secondary = Color(0xFF4B5563),
        tertiary = Color(0xFF374151),
        quaternary = Color(0xFFADAEBC),
    ),
    brand = Color(0xFF6366F1),
    button = Button(
        primary = Color(0xFF6366F1),
        disabled = Color.Transparent,
    ),
    stroke = Stroke(Color(0xFFD1D5DB), Color(0xFFFF6B6B), Color(0xFF6366F1)),
    additional = Additional(
        iconColor = Color(0xFF4B5563),
        white = Color(0xFFFFFFFF)
    )
)