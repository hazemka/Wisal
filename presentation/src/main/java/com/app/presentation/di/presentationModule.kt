package com.app.presentation.di

import com.app.presentation.navigation.NavViewModel
import com.app.presentation.screen.beneficiary_details.BeneficiaryDetailsViewModel
import com.app.presentation.screen.create_new_account.CreateNewAccountViewModel
import com.app.presentation.screen.home.HomeViewModel
import com.app.presentation.screen.login.LoginViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module {
    viewModelOf(::CreateNewAccountViewModel)
    viewModelOf(::LoginViewModel)
    viewModelOf(::NavViewModel)
    viewModelOf(::BeneficiaryDetailsViewModel)
    viewModelOf(::HomeViewModel)
}