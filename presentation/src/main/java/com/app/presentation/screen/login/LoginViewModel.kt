package com.app.presentation.screen.login

import com.app.presentation.base.BaseViewModel

class LoginViewModel(): BaseViewModel<LoginState, LoginEvents>(LoginState())
, LoginInteractionListener{

    override fun onIdNumberValueChanged(idNumber: String) {
        updateState { it.copy(idNumber = idNumber) }
    }

    override fun onPasswordValueChanged(password: String) {
        updateState { it.copy(password = password) }
    }

    override fun onClickLogin() {
        TODO("Not yet implemented")
    }

    override fun onClickForgetPassword() {
        TODO("Not yet implemented")
    }

    override fun onClickCreateNewAccount() {
        TODO("Not yet implemented")
    }
}