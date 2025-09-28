package com.app.design_system.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalConfiguration
import com.app.design_system.color.LocalLanguage
import com.app.design_system.color.LocaleWusalColors
import com.app.design_system.color.darkThemeColor
import com.app.design_system.color.lightThemeColor
import com.app.design_system.typography.DefaultTextStyle
import com.app.design_system.typography.LocalWusalTextStyle

@Composable
fun WuslaTheme(
    isDark: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
){
    val configuration = LocalConfiguration.current
    val deviceLanguage = configuration.locales[0].language ?: "en"
    val supportedLanguages = listOf("en", "ar")
    val appLanguage = if (supportedLanguages.contains(deviceLanguage)) {
        deviceLanguage
    } else {
        "en"
    }

    val colorScheme = if (isDark) darkThemeColor else lightThemeColor

    CompositionLocalProvider(
        LocalLanguage provides appLanguage,
        LocaleWusalColors provides colorScheme,
        LocalWusalTextStyle provides DefaultTextStyle
    ) {
        content()
    }
}