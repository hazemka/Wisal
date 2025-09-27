package com.app.wusla

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.ui.text.style.TextAlign
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.app.design_system.theme.Theme
import com.app.design_system.theme.WuslaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WuslaTheme {
                Text(
                    "Main Activity",
                    textAlign = TextAlign.Center,
                    color = Theme.colors.shade.primary
                )
            }
        }
    }
}