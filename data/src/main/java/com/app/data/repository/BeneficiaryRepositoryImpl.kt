package com.app.data.repository

import com.app.data.data_source.remote.BeneficiaryRemoteDataSource
import com.app.data.mapper.toDomain
import com.app.data.remote.dto.BeneficiaryDetailsRequestDto
import com.app.domain.model.Beneficiary
import com.app.domain.repository.BeneficiaryRepository

class BeneficiaryRepositoryImpl(
    private val beneficiaryRemoteDataSource: BeneficiaryRemoteDataSource
): BeneficiaryRepository {

    override suspend fun getBeneficiaryByNationalId(nationalId: String): Beneficiary {
        return beneficiaryRemoteDataSource.getBeneficiaryByNationalId(nationalId).toDomain()
    }

    override suspend fun updateBeneficiaryDetails(beneficiary: Beneficiary): Boolean {
        return beneficiaryRemoteDataSource.updateBeneficiaryDetails(
            BeneficiaryDetailsRequestDto(
                fullName = beneficiary.fullName,
                phone = beneficiary.phone,
                address = beneficiary.address,
                healthStatus = beneficiary.healthStatus?.name,
                dateOfBirth = beneficiary.dateOfBirth,
                gender = beneficiary.gender?.name,
                housingStatus = beneficiary.housingStatus?.name,
                income = beneficiary.income
            )
        )
    }
}