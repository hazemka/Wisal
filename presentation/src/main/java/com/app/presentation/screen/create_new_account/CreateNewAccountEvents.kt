package com.app.presentation.screen.create_new_account

import com.app.presentation.utlis.StringValue

sealed class CreateNewAccountEvents {
    data class ShowError(val message: StringValue) : CreateNewAccountEvents()
    data object NavigateToRegistrationCompletionScreen : CreateNewAccountEvents()
    data object NavigateToLoginScreen : CreateNewAccountEvents()
}