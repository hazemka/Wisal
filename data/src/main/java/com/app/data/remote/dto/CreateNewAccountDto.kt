package com.app.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateNewAccountDto(
    @SerialName("statusCode")
    val statusCode: Int? = null,
    @SerialName("message")
    val message: String? = null,
    @SerialName("accessToken")
    val accessToken: String? = null,
    @SerialName("refreshToken")
    val refreshToken: String? = null
)
