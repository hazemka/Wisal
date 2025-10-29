package com.app.presentation.screen.beneficiary_details

import android.content.Context
import com.app.domain.model.Gender
import com.app.domain.model.HealthStatus
import com.app.domain.model.HousingStatus

interface BeneficiaryDetailsInteractionListener {
    fun onDateSelected(date: String)
    fun onGenderSelected(context: Context,gender: Gender)
    fun onHealthStatusSelected(context: Context,healthStatus: HealthStatus)
    fun onAddressValueChanged(address: String)
    fun onHouseSelected(context: Context,housingStatus: HousingStatus)
    fun onNextButtonClicked()
    fun onBackButtonClicked()
    fun onIncomeValueChanged(income: String)
    fun onNumberOfFamilySelected(number: String)
    fun onFamilyMemberChanged(index: Int,familyMember: FamilyMember)
    fun onCompleteRegistrationClicked()
}