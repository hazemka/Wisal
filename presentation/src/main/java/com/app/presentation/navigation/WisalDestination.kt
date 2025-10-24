package com.app.presentation.navigation

sealed class WisalScreens(val route: String) {
    object CreateNewAccountScreen: WisalScreens("createNewAccount")
    object LoginScreen: WisalScreens("login")
    object HomeScreen: WisalScreens("home")
}