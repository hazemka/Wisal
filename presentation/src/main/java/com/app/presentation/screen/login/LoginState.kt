package com.app.presentation.screen.login

data class LoginState(
    val idNumber: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
)