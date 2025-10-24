package com.app.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequestDto(
    @SerialName("nationalId") val nationalId: String,
    @SerialName("password") val password: String
)
