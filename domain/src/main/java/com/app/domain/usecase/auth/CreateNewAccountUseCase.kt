package com.app.domain.usecase.auth

import com.app.domain.model.Beneficiary
import com.app.domain.repository.AuthenticationRepository

class CreateNewAccountUseCase(
    private val authenticationRepository: AuthenticationRepository
){
    suspend operator fun invoke(beneficiary: Beneficiary, password: String) =
        authenticationRepository.createNewAccount(beneficiary = beneficiary,password = password)
}