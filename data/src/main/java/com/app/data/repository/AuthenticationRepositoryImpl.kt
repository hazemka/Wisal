package com.app.data.repository

import com.app.data.data_source.remote.AuthenticationRemoteDataSource
import com.app.data.remote.dto.CreateNewAccountRequestDto
import com.app.domain.model.Beneficiary
import com.app.domain.repository.AuthenticationRepository

class AuthenticationRepositoryImpl(
    private val authenticationRemoteDataSource: AuthenticationRemoteDataSource
) : AuthenticationRepository{

    override suspend fun createNewAccount(
        beneficiary: Beneficiary,
        password: String
    ): Boolean {
        val result = authenticationRemoteDataSource.createNewAccount(
            CreateNewAccountRequestDto(
                fullName = beneficiary.fullName,
                nationalId = beneficiary.nationalId,
                phone = beneficiary.phone,
                password = password
            )
        )
        return result.accessToken != null && result.refreshToken != null
    }

    override suspend fun login(nationalId: String, password: String): Boolean {
        val result = authenticationRemoteDataSource.login(nationalId,password)
        return result.accessToken != null && result.refreshToken != null
    }

}