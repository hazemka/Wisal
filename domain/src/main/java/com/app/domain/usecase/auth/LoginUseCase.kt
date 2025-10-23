package com.app.domain.usecase.auth

import com.app.domain.repository.AuthenticationRepository

class LoginUseCase(
    private val authenticationRepository: AuthenticationRepository
) {
    suspend operator fun invoke(nationalId: String,password: String) =
        authenticationRepository.login(nationalId = nationalId, password = password)
}