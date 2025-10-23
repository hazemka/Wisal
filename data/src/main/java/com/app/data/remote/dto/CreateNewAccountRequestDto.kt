package com.app.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateNewAccountRequestDto(
    @SerialName("fullName") val fullName: String,
    @SerialName("nationalId") val nationalId: String,
    @SerialName("phone") val phone: String,
    @SerialName("password") val password: String
)
