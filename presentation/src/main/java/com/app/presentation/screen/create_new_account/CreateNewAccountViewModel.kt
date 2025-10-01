package com.app.presentation.screen.create_new_account

import com.app.presentation.screen.base.BaseViewModel

class CreateNewAccountViewModel() :
    BaseViewModel<CreateNewAccountState, CreateNewAccountEvents>(CreateNewAccountState()),
    CreateNewAccountInteractionListener {

    override fun onFullNameValueChanged(fullName: String) {
        updateState { it.copy(fullName = fullName) }
    }

    override fun onIdNumberValueChanged(idNumber: String) {
        updateState { it.copy(idNumber = idNumber) }
    }

    override fun onPhoneNumberValueChanged(phoneNumber: String) {
        updateState { it.copy(phoneNumber = phoneNumber) }
    }

    override fun onPasswordValueChanged(password: String) {
        updateState { it.copy(password = password) }
    }

    override fun onPasswordConfirmationValueChanged(passwordConfirmation: String) {
        updateState { it.copy(passwordConfirmation = passwordConfirmation) }
    }

    override fun onTermsAndConditionCheckedChanged(isChecked: Boolean) {
        updateState { it.copy(agreeToTermsAndConditions = isChecked) }
    }

    override fun onClickCreateAccount() {
        TODO("Not yet implemented")
    }

    override fun onClickTermsAndConditions() {
        TODO("Not yet implemented")
    }

    override fun onClickGoToLogin() {
        TODO("Not yet implemented")
    }

}