package com.app.data.data_source.remote

import com.app.data.remote.dto.BeneficiaryDetailsRequestDto
import com.app.data.remote.dto.BeneficiaryDetailsResponseDto
import com.app.data.remote.dto.FamilyMemberDto

interface BeneficiaryRemoteDataSource {
    suspend fun getBeneficiaryByNationalId(nationalId: String): BeneficiaryDetailsResponseDto
    suspend fun updateBeneficiaryDetails(beneficiaryDetailsRequestDto: BeneficiaryDetailsRequestDto): Boolean
    suspend fun createNewFamilyMember(familyMemberDto: FamilyMemberDto): Boolean
}