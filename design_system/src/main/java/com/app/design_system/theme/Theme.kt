package com.app.design_system.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import com.app.design_system.color.LocaleWusalColors
import com.app.design_system.color.WuslaColor
import com.app.design_system.typography.LocalWusalTextStyle
import com.app.design_system.typography.WusalTextStyle

object Theme {
    val colors: WuslaColor
    @Composable
    @ReadOnlyComposable
    get() = LocaleWusalColors.current

    val textStyle : WusalTextStyle
    @Composable
    @ReadOnlyComposable
    get() = LocalWusalTextStyle.current
}