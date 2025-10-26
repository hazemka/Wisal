package com.app.presentation.screen.create_new_account

import androidx.lifecycle.viewModelScope
import com.app.domain.model.Beneficiary
import com.app.domain.usecase.auth.CreateNewAccountUseCase
import com.app.presentation.R
import com.app.presentation.base.BaseViewModel
import com.app.presentation.utlis.StringValue
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CreateNewAccountViewModel(
    private val createNewAccountUseCase: CreateNewAccountUseCase
) :
    BaseViewModel<CreateNewAccountState, CreateNewAccountEvents>(CreateNewAccountState()),
    CreateNewAccountInteractionListener {

    private var idNumberValidationJob: Job? = null
    private var fullNameValidationJob: Job? = null
    private var phoneNumberValidationJob: Job? = null
    private var passwordValidationJob: Job? = null
    private var passwordConfirmationValidationJob: Job? = null

    override fun onFullNameValueChanged(fullName: String) {
        if (fullName.length <= 50) {
            updateState {
                it.copy(
                    fullName = fullName,
                    screenError = null
                )
            }
        }
        fullNameValidationJob?.cancel()
        fullNameValidationJob = validateInput(
            input = fullName,
            rules = listOf(
                {
                    if (it.any { ch -> !ch.isLetter() && ch != ' ' }) StringValue.StringResource(
                        R.string.fullname_must_be_only_letters
                    ) else null
                },
                {
                    if (it.trim().split("\\s+".toRegex()).size < 2)
                        StringValue.StringResource(R.string.enter_at_least_two_names)
                    else null
                }
            ),
            onResult = { error ->
                updateState { it.copy(fullNameError = error) }
            }
        )
    }

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

    override fun onPhoneNumberValueChanged(phoneNumber: String) {
        if (phoneNumber.length <= 10) {
            updateState { it.copy(phoneNumber = phoneNumber) }
        }
        phoneNumberValidationJob?.cancel()
        phoneNumberValidationJob = validateInput(
            input = phoneNumber,
            rules = listOf(
                {
                    if (it.any { c -> !c.isDigit() }) StringValue.StringResource(
                        R.string.only_numbers_are_allowed
                    ) else null
                },
                { if (it.length < 10) StringValue.StringResource(R.string.you_must_enter_10_digits) else null }
            ),
            onResult = { error ->
                updateState { it.copy(phoneNumberError = error) }
            }
        )
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

    override fun onPasswordConfirmationValueChanged(passwordConfirmation: String) {
        if (passwordConfirmation.length <= 25) {
            updateState { it.copy(passwordConfirmation = passwordConfirmation) }
        }
        passwordConfirmationValidationJob?.cancel()
        passwordConfirmationValidationJob = validateInput(
            input = passwordConfirmation,
            rules = listOf(
                { if (it.length !in 10..25) StringValue.StringResource(R.string.password_must_be_10_25_characters) else null },
                {
                    val hasLetter = it.any { c -> c.isLetter() }
                    val hasDigit = it.any { c -> c.isDigit() }
                    val hasSpecial = it.any { c -> !c.isLetterOrDigit() }

                    if (!hasLetter || !hasDigit || !hasSpecial)
                        StringValue.StringResource(R.string.password_must_include_letters_digits_and_special_chars)
                    else null
                },
                {
                    if (uiState.value.password != passwordConfirmation) StringValue.StringResource(
                        R.string.password_does_not_match
                    ) else null
                }
            ),
            onResult = { error ->
                updateState { it.copy(passwordConfirmationError = error) }
            }
        )
    }

    override fun onTermsAndConditionCheckedChanged(isChecked: Boolean) {
        updateState { it.copy(agreeToTermsAndConditions = isChecked) }
    }

    override fun onClickCreateAccount() {
        updateState { it.copy(isLoading = true) }
        launchWithResult(
            action = {
                createNewAccountUseCase.invoke(
                    Beneficiary(
                        fullName = uiState.value.fullName, nationalId =  uiState.value.idNumber, phone =  uiState.value.phoneNumber
                    ), uiState.value.password
                )
            }, onSuccess = {
                updateState { it.copy(isLoading = false) }
                // go to complete register
            },
            onError = {
                updateState { it.copy(isLoading = false) }
                sendEvent(
                    CreateNewAccountEvents.ShowError(
                        StringValue.StringResource(
                            R.string.something_went_wrong
                        )
                    )
                )
            }
        )
    }

    override fun onClickTermsAndConditions() {}

    override fun onClickGoToLogin() {
        sendEvent(CreateNewAccountEvents.NavigateToLoginScreen)
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