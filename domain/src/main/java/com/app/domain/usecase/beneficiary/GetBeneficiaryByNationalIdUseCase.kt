package com.app.domain.usecase.beneficiary

import com.app.domain.repository.BeneficiaryRepository

class GetBeneficiaryByNationalIdUseCase(
    private val beneficiaryRepository: BeneficiaryRepository
) {
    suspend operator fun invoke(nationalId: String) =
        beneficiaryRepository.getBeneficiaryByNationalId(nationalId)
}