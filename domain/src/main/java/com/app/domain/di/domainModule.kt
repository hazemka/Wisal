package com.app.domain.di

import com.app.domain.usecase.auth.CreateNewAccountUseCase
import com.app.domain.usecase.auth.LoginUseCase
import com.app.domain.usecase.beneficiary.GetBeneficiaryByNationalIdUseCase
import com.app.domain.usecase.beneficiary.UpdateBeneficiaryDetailsUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { CreateNewAccountUseCase(get()) }
    factory { LoginUseCase(get()) }
    factory { GetBeneficiaryByNationalIdUseCase(get()) }
    factory { UpdateBeneficiaryDetailsUseCase(get()) }
}