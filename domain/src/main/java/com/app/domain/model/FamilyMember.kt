package com.app.domain.model

data class FamilyMember(
    val fullName: String,
    val nationalId: String,
    val healthStatus: HealthStatus,
    val relationship: Relation,
    val dateOfBirth: String,
    val gender: Gender
)
