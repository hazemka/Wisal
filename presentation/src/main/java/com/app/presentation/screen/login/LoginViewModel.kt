package com.app.presentation.screen.login

import androidx.lifecycle.viewModelScope
import com.app.domain.usecase.auth.LoginUseCase
import com.app.presentation.R
import com.app.presentation.base.BaseViewModel
import com.app.presentation.utlis.StringValue
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : BaseViewModel<LoginState, LoginEvents>(LoginState()), LoginInteractionListener {

    private var idNumberValidationJob: Job? = null
    private var passwordValidationJob: Job? = null

    override fun onIdNumberValueChanged(idNumber: String) {
        if (idNumber.length <= 9) {
            updateState {
                it.copy(idNumber = idNumber)
            }
        }
        idNumberValidationJob?.cancel()
        idNumberValidationJob = validateInput(
            input = idNumber, rules = listOf(
                {
                    if (it.any { c -> !c.isDigit() }) StringValue.StringResource(
                        R.string.only_numbers_are_allowed
                    ) else null
                },
                { if (it.length < 9) StringValue.StringResource(R.string.you_must_enter_9_digits) else null }
            ), onResult = { error ->
                updateState { it.copy(idNumberError = error) }
            })
    }

    override fun onPasswordValueChanged(password: String) {
        if (password.length <= 25) {
            updateState { it.copy(password = password) }
        }
        passwordValidationJob?.cancel()
        passwordValidationJob = validateInput(
            input = password,
            rules = listOf(
                { if (it.length !in 10..25) StringValue.StringResource(R.string.password_must_be_10_25_characters) else null },
                {
                    val hasLetter = it.any { c -> c.isLetter() }
                    val hasDigit = it.any { c -> c.isDigit() }
                    val hasSpecial = it.any { c -> !c.isLetterOrDigit() }

                    if (!hasLetter || !hasDigit || !hasSpecial)
                        StringValue.StringResource(R.string.password_must_include_letters_digits_and_special_chars)
                    else null
                }
            ),
            onResult = { error ->
                updateState { it.copy(passwordError = error) }
            }
        )
    }

    override fun onClickLogin() {
        updateState { it.copy(isLoading = true) }
        launchWithResult(
            action = {
                loginUseCase.invoke(
                    nationalId = uiState.value.idNumber, password = uiState.value.password
                )
            }, onSuccess = {
                updateState { it.copy(isLoading = false) }
                // go to complete register
            },
            onError = {
                updateState { it.copy(isLoading = false) }
                sendEvent(
                    LoginEvents.ShowError(
                        StringValue.StringResource(
                            R.string.incorrect_id_number_or_password
                        )
                    )
                )
            }
        )
    }

    override fun onClickForgetPassword() {
        TODO("Not yet implemented")
    }

    override fun onClickCreateNewAccount() {
        sendEvent(LoginEvents.NavigateToCreateNewAccountScreen)
    }

    private fun validateInput(
        input: String,
        delayMillis: Long = 500,
        rules: List<(String) -> StringValue?>,
        onResult: (StringValue?) -> Unit
    ): Job {
        return viewModelScope.launch {
            delay(delayMillis)
            val trimmed = input.trim()
            // Run through rules in order — stop at first error
            val error = rules.firstNotNullOfOrNull { rule -> rule(trimmed) }
            onResult(error)
        }
    }
}