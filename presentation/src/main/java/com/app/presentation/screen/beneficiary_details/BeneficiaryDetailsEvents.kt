package com.app.presentation.screen.beneficiary_details

sealed class BeneficiaryDetailsEvents {
    data object NavigateToLoginScreen: BeneficiaryDetailsEvents()
}