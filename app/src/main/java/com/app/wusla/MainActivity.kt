package com.app.wusla

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.app.design_system.theme.WuslaTheme
import com.app.presentation.screen.create_new_account.CreateNewAccountScreen
import com.app.presentation.screen.login.LoginScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WuslaTheme {
                LoginScreen()
            }
        }
    }
}