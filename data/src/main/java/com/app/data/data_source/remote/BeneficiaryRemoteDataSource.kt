package com.app.data.data_source.remote

import com.app.data.remote.dto.BeneficiaryDetailsRequestDto
import com.app.data.remote.dto.BeneficiaryDetailsResponseDto

interface BeneficiaryRemoteDataSource {
    suspend fun getBeneficiaryByNationalId(nationalId: String): BeneficiaryDetailsResponseDto
    suspend fun updateBeneficiaryDetails(beneficiaryDetailsRequestDto: BeneficiaryDetailsRequestDto): Boolean
}