package com.app.presentation.screen.create_new_account

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.design_system.component.button.WusalButton
import com.app.design_system.component.text_field.WusalTextField
import com.app.design_system.theme.Theme
import com.app.presentation.R
import com.app.presentation.navigation.WisalScreens
import org.koin.androidx.compose.koinViewModel

@Composable
fun CreateNewAccountScreen(
    navController: NavController,
    viewModel: CreateNewAccountViewModel = koinViewModel(),
) {
    val state by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(viewModel) {
        viewModel.uiEffect.collect { event ->
            when (event) {
                is CreateNewAccountEvents.NavigateToLoginScreen -> {
                    if (navController.previousBackStackEntry != null){
                        navController.popBackStack()
                    }else
                        navController.navigate(route = WisalScreens.LoginScreen.route)
                }

                is CreateNewAccountEvents.NavigateToRegistrationCompletionScreen -> {

                }

                is CreateNewAccountEvents.ShowError -> {
                    Toast.makeText(context, event.message.asString(context), Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    CreateNewAccountScreenContent(
        state = state,
        interactionListener = viewModel,
        context = context
    )
}

@Composable
fun CreateNewAccountScreenContent(
    state: CreateNewAccountState,
    interactionListener: CreateNewAccountInteractionListener,
    context: Context
) {

    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .background(color = Theme.colors.background.customScreen)
            .fillMaxWidth()
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.pure_wisal_logo),
            contentDescription = stringResource(
                R.string.wisal_logo
            ),
            modifier = Modifier
                .padding(top = 32.dp)
        )
        Text(
            text = stringResource(R.string.welcome_to_wisal),
            style = Theme.textStyle.bold.copy(fontSize = 24.sp),
            color = Theme.colors.additional.white,
            modifier = Modifier.padding(top = 10.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(R.string.an_app_to_connect_you_with_the_aid_you_deserve),
            style = Theme.textStyle.regular.copy(fontSize = 14.sp),
            color = Theme.colors.additional.white.copy(alpha = 0.9f),
            modifier = Modifier.padding(top = 7.dp, bottom = 32.dp),
            textAlign = TextAlign.Center
        )
        Column(
            modifier = Modifier
                .background(
                    color = Theme.colors.additional.white, shape = RoundedCornerShape(
                        topStart = 24.dp, topEnd = 24.dp
                    )
                )
                .padding(start = 16.dp, end = 16.dp, bottom = 64.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.create_account),
                style = Theme.textStyle.bold.copy(fontSize = 24.sp),
                color = Theme.colors.shade.primary,
                modifier = Modifier.padding(top = 32.dp)
            )
            Text(
                text = stringResource(R.string.register_to_start),
                style = Theme.textStyle.regular.copy(fontSize = 14.sp),
                color = Theme.colors.shade.secondary,
                modifier = Modifier.padding(top = 7.dp)
            )
            WusalTextField(
                modifier = Modifier
                    .padding(top = 32.dp),
                value = state.fullName,
                label = stringResource(R.string.full_name),
                leadingIcon = painterResource(R.drawable.profile_ic),
                leadingIconTint = Theme.colors.additional.iconColor,
                onValueChange = interactionListener::onFullNameValueChanged,
                placeholder = stringResource(R.string.enter_your_full_name),
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text,
                    autoCorrectEnabled = true
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                ),
                isError = state.fullNameError != null,
                errorMessage = state.fullNameError?.asString(context = context)
            )
            WusalTextField(
                modifier = Modifier
                    .padding(top = 16.dp),
                value = state.idNumber,
                label = stringResource(R.string.id_number),
                leadingIcon = painterResource(R.drawable.id_ic),
                leadingIconTint = Theme.colors.additional.iconColor,
                onValueChange = interactionListener::onIdNumberValueChanged,
                placeholder = stringResource(R.string.enter_your_id_number),
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Number,
                    autoCorrectEnabled = true
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                ),
                isError = state.idNumberError != null,
                errorMessage = state.idNumberError?.asString(context)
            )
            WusalTextField(
                modifier = Modifier
                    .padding(top = 16.dp),
                value = state.phoneNumber,
                label = stringResource(R.string.phone_number),
                leadingIcon = painterResource(R.drawable.phone_ic),
                leadingIconTint = Theme.colors.additional.iconColor,
                onValueChange = interactionListener::onPhoneNumberValueChanged,
                placeholder = stringResource(R.string.enter_your_phone_number),
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Phone,
                    autoCorrectEnabled = true
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                ),
                isError = state.phoneNumberError != null,
                errorMessage = state.phoneNumberError?.asString(context)
            )
            WusalTextField(
                modifier = Modifier
                    .padding(top = 16.dp),
                value = state.password,
                isPassword = true,
                label = stringResource(R.string.password),
                leadingIcon = painterResource(R.drawable.lock_ic),
                leadingIconTint = Theme.colors.additional.iconColor,
                onValueChange = interactionListener::onPasswordValueChanged,
                placeholder = stringResource(R.string.enter_a_password),
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Password,
                    autoCorrectEnabled = false
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                ),
                isError = state.passwordError != null,
                errorMessage = state.passwordError?.asString(context)
            )
            WusalTextField(
                modifier = Modifier
                    .padding(top = 16.dp),
                value = state.passwordConfirmation,
                isPassword = true,
                label = stringResource(R.string.confirm_password),
                leadingIcon = painterResource(R.drawable.lock_ic),
                leadingIconTint = Theme.colors.additional.iconColor,
                onValueChange = interactionListener::onPasswordConfirmationValueChanged,
                placeholder = stringResource(R.string.confirm_password),
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Password,
                    autoCorrectEnabled = false
                ),
                keyboardActions = KeyboardActions(
                    onDone = { focusManager.clearFocus() }
                ),
                isError = state.passwordConfirmationError != null,
                errorMessage = state.passwordConfirmationError?.asString(context)
            )
            Row(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .align(Alignment.Start),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = state.agreeToTermsAndConditions,
                    onCheckedChange = interactionListener::onTermsAndConditionCheckedChanged,
                    colors = CheckboxDefaults.colors(
                        checkmarkColor = Theme.colors.additional.white,
                        uncheckedColor = Theme.colors.shade.secondary,
                        checkedColor = Theme.colors.brand
                    )
                )
                Text(
                    stringResource(R.string.i_agree_to),
                    style = Theme.textStyle.regular.copy(fontSize = 14.sp),
                    color = Theme.colors.shade.secondary
                )
                TextButton(
                    onClick = interactionListener::onClickTermsAndConditions,
                    contentPadding = PaddingValues(start = 2.dp)
                ) {
                    Text(
                        stringResource(R.string.terms_and_privacy_policy),
                        style = Theme.textStyle.regular.copy(fontSize = 14.sp),
                        color = Theme.colors.brand
                    )
                }
            }
            WusalButton(
                modifier = Modifier.padding(top = 16.dp),
                buttonText = stringResource(R.string.create_account),
                buttonColor = Theme.colors.button.primary,
                textColor = Theme.colors.additional.white,
                textStyle = Theme.textStyle.medium.copy(fontSize = 18.sp),
                onClick = interactionListener::onClickCreateAccount,
                enableAction = (state.fullNameError == null && state.idNumberError == null && state.phoneNumberError == null
                        && state.passwordError == null && state.passwordConfirmationError == null) &&
                        state.fullName.isNotBlank() && state.idNumber.isNotBlank() && state.phoneNumber.isNotBlank() &&
                        state.password.isNotBlank() && state.passwordConfirmation.isNotBlank() && state.agreeToTermsAndConditions
            )
            Row(
                modifier = Modifier.padding(top = 32.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    stringResource(R.string.do_you_already_have_an_account),
                    style = Theme.textStyle.regular.copy(fontSize = 14.sp),
                    color = Theme.colors.shade.secondary
                )
                Text(
                    stringResource(R.string.login),
                    style = Theme.textStyle.regular.copy(fontSize = 14.sp),
                    color = Theme.colors.brand,
                    modifier = Modifier
                        .padding(3.dp)
                        .clickable {
                            interactionListener.onClickGoToLogin()
                        }
                )
            }
            AnimatedVisibility(state.isLoading) {
                Dialog(
                    onDismissRequest = { },
                    DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(100.dp)
                            .background(
                                Theme.colors.additional.white,
                                shape = RoundedCornerShape(8.dp)
                            )
                    ) {
                        CircularProgressIndicator(color = Theme.colors.button.primary)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun CreateNewAccountScreenPreview() {
    val navController = rememberNavController()
    CreateNewAccountScreen(navController)
}
