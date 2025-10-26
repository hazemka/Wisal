package com.app.domain.repository

import com.app.domain.model.Beneficiary

interface BeneficiaryRepository {
    suspend fun getBeneficiaryByNationalId(nationalId: String): Beneficiary
    suspend fun updateBeneficiaryDetails(beneficiary: Beneficiary): Boolean
}