package com.app.data.remote.service

import com.app.data.remote.dto.CreateNewAccountDto
import com.app.data.remote.dto.CreateNewAccountRequestDto
import com.app.data.remote.dto.LoginDto
import com.app.data.remote.dto.LoginRequestDto
import com.app.data.utlis.BENEFICIARY_AUTH
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationService {

    @POST("$BENEFICIARY_AUTH/Signup")
    suspend fun createNewAccount(
        @Body accountRequestDto: CreateNewAccountRequestDto
    ): Response<CreateNewAccountDto>

    @POST("$BENEFICIARY_AUTH/Login")
    suspend fun login(
        @Body loginRequestDto: LoginRequestDto
    ): Response<LoginDto>

}