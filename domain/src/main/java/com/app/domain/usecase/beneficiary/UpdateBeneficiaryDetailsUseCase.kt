package com.app.domain.usecase.beneficiary

import com.app.domain.model.Beneficiary
import com.app.domain.repository.BeneficiaryRepository

class UpdateBeneficiaryDetailsUseCase(
    private val beneficiaryRepository: BeneficiaryRepository
) {
    suspend operator fun invoke(beneficiary: Beneficiary) =
        beneficiaryRepository.updateBeneficiaryDetails(beneficiary)
}