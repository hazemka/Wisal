package com.app.design_system.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.app.design_system.color.LocalLanguage
import com.app.design_system.color.LocaleWusalColors
import com.app.design_system.color.darkThemeColor
import com.app.design_system.color.lightThemeColor
import com.app.design_system.typography.DefaultTextStyle
import com.app.design_system.typography.LocalWusalTextStyle

@Composable
fun WuslaTheme(
    language: String = "ar",
    isDark: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
){
    val colorScheme = if (isDark) darkThemeColor else lightThemeColor
    CompositionLocalProvider(
        LocalLanguage provides language,
        LocaleWusalColors provides colorScheme,
        LocalWusalTextStyle provides DefaultTextStyle
    ) {
        content()
    }
}