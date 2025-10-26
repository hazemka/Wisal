package com.app.data.remote.service

import com.app.data.remote.dto.BeneficiaryDetailsRequestDto
import com.app.data.remote.dto.BeneficiaryDetailsResponseDto
import com.app.data.remote.dto.UpdateBeneficiaryDto
import com.app.data.utlis.BENEFICIARIES
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface BeneficiaryService {

    @GET("$BENEFICIARIES/national/{nationalId}")
    suspend fun getBeneficiaryByNationalId(
        @Path("nationalId") nationalId: String
    ): Response<BeneficiaryDetailsResponseDto>

    @PATCH("$BENEFICIARIES/{id}")
    suspend fun updateBeneficiaryDetails(
        @Path("id") id: String,
        @Body beneficiaryDetailsRequestDto: BeneficiaryDetailsRequestDto
    ): Response<UpdateBeneficiaryDto>

}