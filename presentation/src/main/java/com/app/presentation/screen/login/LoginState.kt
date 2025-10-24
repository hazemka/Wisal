package com.app.presentation.screen.login

import com.app.presentation.utlis.StringValue

data class LoginState(
    val idNumber: String = "",
    val password: String = "",
    val idNumberError: StringValue? = null,
    val passwordError: StringValue? = null,
    val isLoading: Boolean = false,
)