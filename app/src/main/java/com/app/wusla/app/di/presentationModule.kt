package com.app.wusla.app.di

import com.app.presentation.screen.create_new_account.CreateNewAccountViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module {
    viewModelOf(::CreateNewAccountViewModel)
}