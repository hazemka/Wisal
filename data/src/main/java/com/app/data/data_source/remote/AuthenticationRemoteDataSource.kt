package com.app.data.data_source.remote

import com.app.data.remote.dto.CreateNewAccountDto
import com.app.data.remote.dto.CreateNewAccountRequestDto
import com.app.data.remote.dto.LoginDto

interface AuthenticationRemoteDataSource {
    suspend fun createNewAccount(createNewAccountRequestDto: CreateNewAccountRequestDto): CreateNewAccountDto
    suspend fun login(nationalId: String,password: String): LoginDto
}