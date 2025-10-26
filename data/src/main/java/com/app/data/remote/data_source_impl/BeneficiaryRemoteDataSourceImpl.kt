package com.app.data.remote.data_source_impl

import android.util.Log
import com.app.data.data_source.remote.BeneficiaryRemoteDataSource
import com.app.data.remote.dto.BeneficiaryDetailsRequestDto
import com.app.data.remote.dto.BeneficiaryDetailsResponseDto
import com.app.data.remote.service.BeneficiaryService
import com.app.domain.repository.PreferencesRepository

class BeneficiaryRemoteDataSourceImpl(
    private val beneficiaryService: BeneficiaryService,
    private val preferencesRepository: PreferencesRepository
) : BeneficiaryRemoteDataSource {

    override suspend fun getBeneficiaryByNationalId(nationalId: String): BeneficiaryDetailsResponseDto {
        val response = beneficiaryService.getBeneficiaryByNationalId(nationalId = nationalId)
        if (response.isSuccessful) {
            preferencesRepository.saveUserId(response.body()?.id)
        }
        Log.e("hzm", "get id response ${response.body()}")
        return response.body() ?: BeneficiaryDetailsResponseDto()
    }

    override suspend fun updateBeneficiaryDetails(beneficiaryDetailsRequestDto: BeneficiaryDetailsRequestDto): Boolean {
        val id = preferencesRepository.getUserId()
        if (!id.isNullOrBlank()){
            val response = beneficiaryService.updateBeneficiaryDetails(id,beneficiaryDetailsRequestDto)
            Log.e("hzm", "update response ${response.body()} ///// is success ${response.isSuccessful}")
            return response.isSuccessful
        }
        return false
    }


}

