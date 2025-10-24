package com.app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import com.app.presentation.screen.create_new_account.createNewAccountRoute
import com.app.presentation.screen.login.loginRoute
import org.koin.androidx.compose.koinViewModel

@Composable
fun WisalNavGraph(
    navHostController: NavHostController,
    viewModel: NavViewModel = koinViewModel()
){
    val startDestination = viewModel.startDestination.value
    if (startDestination == null) return // splash is still shown
    NavHost(
        navController = navHostController,
        startDestination = startDestination,
    ){
        createNewAccountRoute(navHostController)
        loginRoute(navHostController)
    }
}