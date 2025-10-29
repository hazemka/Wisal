package com.app.domain.usecase.beneficiary

import com.app.domain.model.FamilyMember
import com.app.domain.repository.BeneficiaryRepository

class CreateNewFamilyMemberUseCase(
    private val beneficiaryRepository: BeneficiaryRepository
) {
    suspend operator fun invoke(familyMember: FamilyMember) =
        beneficiaryRepository.createNewFamilyMember(familyMember)
}