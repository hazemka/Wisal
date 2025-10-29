package com.app.presentation.screen.beneficiary_details

import com.app.domain.model.Gender
import com.app.domain.model.HealthStatus
import com.app.domain.model.HousingStatus
import com.app.domain.model.Relation

data class BeneficiaryDetailsState(
    val currentForm: Int = 1,
    val familyCount: Int = 0,
    val dateOfBarth: String = "",
    val gender: String = "",
    val address: String = "",
    val houseType: String = "",
    val monthlyIncome: String = "",
    val familyMembers: List<FamilyMember> = emptyList(),
    val healthStatus: String = "",
    val healthStatusBackend: HealthStatus = HealthStatus.NORMAL,
    val genderBackend: Gender = Gender.MALE,
    val houseBackend: HousingStatus = HousingStatus.OWNED,
    val isLoading: Boolean = false
    )

data class FamilyMember(
    val name: String = "",
    val idNumber: String = "",
    val dateOfBarth: String = "",
    val gender: String = "",
    val healthStatus: String = "",
    val genderBackend: Gender = Gender.MALE,
    val healthStatusBackend: HealthStatus = HealthStatus.NORMAL,
    val relation: String = "",
    val relationBackend: Relation = Relation.CHILD
)