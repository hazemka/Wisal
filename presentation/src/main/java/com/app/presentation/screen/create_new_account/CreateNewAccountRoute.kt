package com.app.presentation.screen.create_new_account

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.app.presentation.navigation.WisalScreens

fun NavGraphBuilder.createNewAccountRoute(navController: NavController){
    composable(
        route = WisalScreens.CreateNewAccountScreen.route
    ) {
        CreateNewAccountScreen(navController)
    }
}
