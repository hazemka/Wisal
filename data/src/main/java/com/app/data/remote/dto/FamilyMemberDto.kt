package com.app.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FamilyMemberDto(
    @SerialName("fullName") val fullName: String? = null,
    @SerialName("nationalId") val nationalId: String? = null,
    @SerialName("healthStatus") val healthStatus: String? = null,
    @SerialName("relationship") val relationship: String? = null,
    @SerialName("dateOfBirth") val dateOfBirth: String? = null,
    @SerialName("gender") val gender: String? = null
)
