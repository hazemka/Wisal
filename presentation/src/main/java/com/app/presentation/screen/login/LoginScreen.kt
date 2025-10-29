package com.app.presentation.screen.login

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.design_system.component.button.WusalButton
import com.app.design_system.component.text_field.WusalTextField
import com.app.design_system.theme.Theme
import com.app.presentation.R
import org.koin.androidx.compose.koinViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.presentation.navigation.WisalScreens

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    LaunchedEffect(viewModel) {
        viewModel.uiEffect.collect { event ->
            when (event) {
                is LoginEvents.ShowError -> {
                    Toast.makeText(context, event.message.asString(context), Toast.LENGTH_LONG)
                        .show()
                }

                LoginEvents.NavigateToCreateNewAccountScreen -> {
                    navController.navigate(route = WisalScreens.CreateNewAccountScreen.route)
                }

                LoginEvents.NavigateToHomeScreen -> {
                    navController.navigate(route = WisalScreens.HomeScreen.route){
                        popUpTo(WisalScreens.LoginScreen.route) {
                            inclusive = true
                        }
                    }
                }
            }
        }
    }
    LoginScreenContent(state = state, interactionListener = viewModel, context)
}

@Composable
fun LoginScreenContent(
    state: LoginState,
    interactionListener: LoginInteractionListener,
    context: Context
) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.colors.background.defaultScreen)
            .systemBarsPadding()
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
            text = stringResource(R.string.welcome_back),
            style = Theme.textStyle.bold.copy(fontSize = 24.sp),
            color = Theme.colors.shade.primary,
            modifier = Modifier.padding(top = 10.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(R.string.login_to_your_account_or_create_a_new_account),
            style = Theme.textStyle.regular.copy(fontSize = 14.sp),
            color = Theme.colors.shade.secondary,
            modifier = Modifier.padding(top = 7.dp, bottom = 32.dp),
            textAlign = TextAlign.Center
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
            errorMessage = state.idNumberError?.asString(context = context)
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
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password,
                autoCorrectEnabled = false
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.clearFocus() }
            ),
            isError = state.passwordError != null,
            errorMessage = state.passwordError?.asString(context)
        )
        Text(
            stringResource(R.string.forget_password),
            style = Theme.textStyle.regular.copy(fontSize = 14.sp),
            color = Theme.colors.brand,
            modifier = Modifier
                .align(Alignment.End)
                .padding(3.dp)
                .clickable {
                    interactionListener::onClickForgetPassword
                }
        )
        WusalButton(
            modifier = Modifier.padding(top = 16.dp)
                .fillMaxWidth(),
            buttonText = stringResource(R.string.login),
            buttonColor = Theme.colors.button.primary,
            textColor = Theme.colors.additional.white,
            textStyle = Theme.textStyle.medium.copy(fontSize = 18.sp),
            onClick = interactionListener::onClickLogin,
            enableAction = state.idNumberError == null && state.passwordError == null && state.idNumber.isNotBlank()
                    && state.password.isNotBlank()
        )
        Row(
            modifier = Modifier.padding(top = 32.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                stringResource(R.string.don_t_have_an_account),
                style = Theme.textStyle.regular.copy(fontSize = 14.sp),
                color = Theme.colors.shade.secondary
            )
            Text(
                stringResource(R.string.create_account),
                style = Theme.textStyle.regular.copy(fontSize = 14.sp),
                color = Theme.colors.brand,
                modifier = Modifier
                    .padding(3.dp)
                    .clickable {
                        interactionListener.onClickCreateNewAccount()
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

@Preview
@Composable
fun LoginScreenPreview() {
    val navController = rememberNavController()
    LoginScreen(navController)
}