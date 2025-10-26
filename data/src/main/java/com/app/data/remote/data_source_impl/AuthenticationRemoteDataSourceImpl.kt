package com.app.data.remote.data_source_impl

import com.app.data.data_source.remote.AuthenticationRemoteDataSource
import com.app.data.remote.dto.CreateNewAccountDto
import com.app.data.remote.dto.CreateNewAccountRequestDto
import com.app.data.remote.dto.LoginDto
import com.app.data.remote.dto.LoginRequestDto
import com.app.data.remote.service.AuthenticationService
import com.app.data.utlis.handleApi

class AuthenticationRemoteDataSourceImpl(
    private val authenticationService: AuthenticationService
) : AuthenticationRemoteDataSource{

    override suspend fun createNewAccount(createNewAccountRequestDto: CreateNewAccountRequestDto)
    : CreateNewAccountDto = handleApi {
        authenticationService.createNewAccount(createNewAccountRequestDto)
    }


    override suspend fun login(nationalId: String, password: String): LoginDto = handleApi {
        authenticationService.login(LoginRequestDto(nationalId,password))
    }
}