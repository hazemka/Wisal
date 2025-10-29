package com.app.presentation.screen.beneficiary_details

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.app.presentation.navigation.WisalScreens

fun NavGraphBuilder.beneficiaryDetailsRoute(navController: NavController){
    composable(
        route = WisalScreens.BeneficiaryDetails.route
    ) {
        BeneficiaryDetailsScreen(navController)
    }
}