package com.app.domain.repository

import com.app.domain.model.Beneficiary

interface AuthenticationRepository {

    suspend fun createNewAccount(
        beneficiary: Beneficiary,
        password: String
    ): Boolean

    suspend fun login(
        nationalId: String,
        password: String
    ): Boolean
}