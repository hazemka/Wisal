package com.app.domain.di

import com.app.domain.usecase.auth.CreateNewAccountUseCase
import com.app.domain.usecase.auth.LoginUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { CreateNewAccountUseCase(get()) }
    factory { LoginUseCase(get()) }
}