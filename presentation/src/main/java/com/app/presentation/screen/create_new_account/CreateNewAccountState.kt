package com.app.presentation.screen.create_new_account

data class CreateNewAccountState(
    val fullName: String = "",
    val idNumber: String = "",
    val phoneNumber: String = "",
    val password: String = "",
    val passwordConfirmation: String = "",
    val agreeToTermsAndConditions: Boolean = false,
    val isLoading: Boolean = false,
)
