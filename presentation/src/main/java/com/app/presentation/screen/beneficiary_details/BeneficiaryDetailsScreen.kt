package com.app.presentation.screen.beneficiary_details

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.getValue
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.design_system.component.button.WusalButton
import com.app.design_system.theme.Theme
import com.app.presentation.R
import com.app.presentation.navigation.WisalScreens
import org.koin.androidx.compose.koinViewModel

@Composable
fun BeneficiaryDetailsScreen(
    navController: NavController,
    viewModel: BeneficiaryDetailsViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    LaunchedEffect(viewModel) {
        viewModel.uiEffect.collect {event ->
            when(event){
                is BeneficiaryDetailsEvents.NavigateToLoginScreen ->
                {
                    navController.navigate(WisalScreens.LoginScreen.route){
                        popUpTo(WisalScreens.BeneficiaryDetails.route){ inclusive = true }
                    }
                }
            }
        }
    }
    BeneficiaryDetailsScreenContent(state = state, interactionListener = viewModel)
}

@Composable
fun BeneficiaryDetailsScreenContent(
    state: BeneficiaryDetailsState,
    interactionListener: BeneficiaryDetailsInteractionListener
) {
    Column(
        modifier = Modifier.statusBarsPadding()
    ) {
        Column {
            Text(
                text = stringResource(R.string.registration),
                style = Theme.textStyle.bold.copy(fontSize = 18.sp),
                color = Theme.colors.shade.primary,
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(
                modifier = Modifier
                    .background(color = Color(0xFFE5E7EB))
                    .fillMaxWidth()
                    .height(1.dp)
            )
        }
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        ) {
            val progress = when (state.currentForm) {
                1 -> 0.33f
                2 -> 0.66f
                3 -> 0.99f
                else -> 0f
            }
            Row(
                modifier = Modifier
                    .padding(top = 24.dp, bottom = 8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "${stringResource(R.string.step)} ${state.currentForm} ${stringResource(R.string.from)} 3",
                    style = Theme.textStyle.medium.copy(fontSize = 14.sp),
                    color = Theme.colors.brand,
                )
                Text(
                    text = "${(progress*100).toInt()}%",
                    style = Theme.textStyle.regular.copy(fontSize = 14.sp),
                    color = Theme.colors.shade.tertiary,
                )
            }
            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp),
                color = Theme.colors.brand,
                trackColor = Color(0xFFE5E7EB),
                gapSize = (-2).dp
            )
            when (state.currentForm) {
                1 -> PersonalInfoForm(state,interactionListener)
                2 -> HouseInfoForm(state,interactionListener)
                3 -> FamilyInfoForm(state,interactionListener)
            }
        }
        if (state.currentForm != 3){
            Spacer(
                modifier = Modifier.weight(1f)
            )
            Spacer(
                modifier = Modifier
                    .background(color = Color(0xFFE5E7EB))
                    .fillMaxWidth()
                    .height(1.dp)
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 32.dp, start = 16.dp, end = 16.dp)
            ) {
                WusalButton(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .weight(1f),
                    buttonText = stringResource(R.string.back),
                    buttonColor = Theme.colors.additional.white,
                    textColor = Theme.colors.shade.tertiary,
                    textStyle = Theme.textStyle.medium.copy(fontSize = 16.sp),
                    onClick = interactionListener::onBackButtonClicked,
                )
                WusalButton(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .weight(1f),
                    buttonText = stringResource(R.string.next),
                    buttonColor = Theme.colors.button.primary,
                    textColor = Theme.colors.additional.white,
                    textStyle = Theme.textStyle.medium.copy(fontSize = 16.sp),
                    onClick = interactionListener::onNextButtonClicked,
                )
            }
        }
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

@Preview
@Composable
fun BeneficiaryDetailsScreenPreview() {
    val navController = rememberNavController()
    BeneficiaryDetailsScreen(navController)
}