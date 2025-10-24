package com.app.presentation.screen.login

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.app.presentation.navigation.WisalScreens

fun NavGraphBuilder.loginRoute(navController: NavController){
    composable(
        route = WisalScreens.LoginScreen.route
    ) {
        LoginScreen(navController)
    }
}