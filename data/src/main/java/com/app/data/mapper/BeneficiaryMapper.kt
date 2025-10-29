package com.app.data.mapper

import com.app.data.remote.dto.BeneficiaryDetailsResponseDto
import com.app.domain.model.Beneficiary
import com.app.domain.model.Gender
import com.app.domain.model.HealthStatus
import com.app.domain.model.HousingStatus

fun BeneficiaryDetailsResponseDto.toDomain() =
    Beneficiary(
        fullName = this.fullName ?: "",
        nationalId = this.nationalId ?: "",
        phone = this.phone ?: "",
        address = this.address ?: "",
        healthStatus = when (healthStatus?.uppercase()) {
            "NORMAL" -> HealthStatus.NORMAL
            "SPECIAL_NEEDS" -> HealthStatus.SPECIAL_NEEDS
            "CHRONIC_DISEASE" -> HealthStatus.CHRONIC_DISEASE
            "MARTYR" -> HealthStatus.MARTYR
            else -> HealthStatus.NORMAL
        },
        dateOfBirth = this.dateOfBirth,
        housingStatus = when(this.housingStatus){
            "TENT" -> HousingStatus.TENT
            "RENT" -> HousingStatus.RENT
            "OWNED" -> HousingStatus.OWNED
            else -> null
        },
        gender = when(this.gender){
            "MALE" -> Gender.MALE
            "FEMALE" -> Gender.FEMALE
            else -> null
        },
        income = this.income
    )