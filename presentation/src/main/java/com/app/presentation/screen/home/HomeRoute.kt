package com.app.presentation.screen.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.app.presentation.navigation.WisalScreens

fun NavGraphBuilder.homeRoute(navController: NavController){
    composable(
        route = WisalScreens.HomeScreen.route
    ) {
        HomeScreen(navController)
    }
}