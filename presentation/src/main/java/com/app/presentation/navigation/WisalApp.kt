package com.app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.app.design_system.theme.WuslaTheme

@Composable
fun WisalApp(){
    WuslaTheme {
        val navController = rememberNavController()
        WisalNavGraph(navController)
    }
}