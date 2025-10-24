package com.app.wusla

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.app.presentation.navigation.NavViewModel
import com.app.presentation.navigation.WisalApp
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.getValue

class MainActivity : ComponentActivity() {
    private val viewModel: NavViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition {
            viewModel.startDestination.value == null
        }

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WisalApp()
        }
    }
}