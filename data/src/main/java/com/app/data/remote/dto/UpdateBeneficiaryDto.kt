package com.app.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateBeneficiaryDto(
    @SerialName("message") val message: String? = null
)
