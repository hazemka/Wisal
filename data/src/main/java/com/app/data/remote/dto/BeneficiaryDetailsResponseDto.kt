package com.app.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BeneficiaryDetailsResponseDto(
    @SerialName("id") val id: String? = null,
    @SerialName("fullName") val fullName: String? = null,
    @SerialName("nationalId") val nationalId: String? = null,
    @SerialName("phone") val phone: String? = null,
    @SerialName("address") val address: String? = null,
    @SerialName("healthStatus") val healthStatus: String? = null,
    @SerialName("dateOfBirth") val dateOfBirth: String? = null,
    @SerialName("gender") val gender: String? = null,
    @SerialName("housingStatus") val housingStatus: String? = null,
    @SerialName("income") val income: Double? = null,
    @SerialName("active") val isActive: Boolean? = null,
    @SerialName("profileImage") val profileImageUrl: String? = null,
    @SerialName("createdAt") val createdAt: String? = null,
    @SerialName("updatedAt") val updatedAt: String? = null,
)
