package com.app.presentation.screen.login

interface LoginInteractionListener {
    fun onIdNumberValueChanged(idNumber: String)
    fun onPasswordValueChanged(password: String)
    fun onClickLogin()
    fun onClickForgetPassword()
    fun onClickCreateNewAccount()
}