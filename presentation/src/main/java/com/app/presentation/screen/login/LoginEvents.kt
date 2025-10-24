package com.app.presentation.screen.login

import com.app.presentation.utlis.StringValue

sealed class LoginEvents {
    data class ShowError(val message: StringValue) : LoginEvents()
}