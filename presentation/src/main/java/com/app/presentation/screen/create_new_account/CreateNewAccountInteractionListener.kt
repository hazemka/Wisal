package com.app.presentation.screen.create_new_account

interface CreateNewAccountInteractionListener {
    fun onFullNameValueChanged(fullName: String)
    fun onIdNumberValueChanged(idNumber:String)
    fun onPhoneNumberValueChanged(phoneNumber: String)
    fun onPasswordValueChanged(password: String)
    fun onPasswordConfirmationValueChanged(passwordConfirmation: String)
    fun onTermsAndConditionCheckedChanged(isChecked: Boolean)
    fun onClickCreateAccount()
    fun onClickTermsAndConditions()
    fun onClickGoToLogin()
}