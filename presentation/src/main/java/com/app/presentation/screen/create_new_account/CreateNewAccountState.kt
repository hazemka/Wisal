package com.app.presentation.screen.create_new_account

import com.app.presentation.utlis.StringValue

data class CreateNewAccountState(
    val fullName: String = "",
    val idNumber: String = "",
    val phoneNumber: String = "",
    val password: String = "",
    val passwordConfirmation: String = "",
    val fullNameError: StringValue? = null,
    val idNumberError: StringValue? = null,
    val phoneNumberError: StringValue? = null,
    val passwordError: StringValue? = null,
    val passwordConfirmationError: StringValue? = null,
    val screenError: StringValue? = null,
    val agreeToTermsAndConditions: Boolean = false,
    val isLoading: Boolean = false,
)
