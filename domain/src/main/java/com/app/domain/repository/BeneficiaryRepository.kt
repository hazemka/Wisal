package com.app.domain.repository

import com.app.domain.model.Beneficiary
import com.app.domain.model.FamilyMember

interface BeneficiaryRepository {
    suspend fun getBeneficiaryByNationalId(nationalId: String): Beneficiary
    suspend fun updateBeneficiaryDetails(beneficiary: Beneficiary): Boolean
    suspend fun createNewFamilyMember(familyMember: FamilyMember): Boolean
}