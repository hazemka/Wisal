package com.app.domain.model

data class Beneficiary(
    val fullName: String,
    val nationalId: String,
    val phone: String,
    val address: String? = null,
    val healthStatus: HealthStatus? = null,
    val dateOfBirth: String? = null,
    val housingStatus: HousingStatus? = null,
    val gender: Gender? = null,
    val income: Double? = null,
)

enum class Gender {
    MALE, FEMALE
}

enum class HealthStatus {
    NORMAL, CHRONIC_DISEASE, SPECIAL_NEEDS, MARTYR
}

enum class HousingStatus {
    TENT, RENT, OWNED
}

enum class Relation{
    SPOUSE, CHILD, PARENT
}

