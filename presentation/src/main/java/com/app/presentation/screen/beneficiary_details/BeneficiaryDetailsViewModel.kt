package com.app.presentation.screen.beneficiary_details

import android.content.Context
import android.util.Log
import com.app.domain.model.Beneficiary
import com.app.domain.model.Gender
import com.app.domain.model.HealthStatus
import com.app.domain.model.HousingStatus
import com.app.domain.usecase.beneficiary.CreateNewFamilyMemberUseCase
import com.app.domain.usecase.beneficiary.UpdateBeneficiaryDetailsUseCase
import com.app.presentation.base.BaseViewModel
import com.app.presentation.utlis.toLabelResId

class BeneficiaryDetailsViewModel(
    private val updateBeneficiaryDetailsUseCase: UpdateBeneficiaryDetailsUseCase,
    private val createNewFamilyMemberUseCase: CreateNewFamilyMemberUseCase
) :
    BaseViewModel<BeneficiaryDetailsState, BeneficiaryDetailsEvents>(BeneficiaryDetailsState()),
    BeneficiaryDetailsInteractionListener {
    override fun onDateSelected(date: String) {
        updateState { it.copy(dateOfBarth = date) }
    }

    override fun onGenderSelected(context: Context, gender: Gender) {
        updateState {
            it.copy(
                genderBackend = gender,
                gender = gender.toLabelResId(context)
            )
        }
    }

    override fun onHealthStatusSelected(context: Context, healthStatus: HealthStatus) {
        updateState {
            it.copy(
                healthStatusBackend = healthStatus,
                healthStatus = healthStatus.toLabelResId(context)
            )
        }
    }

    override fun onAddressValueChanged(address: String) {
        updateState { it.copy(address = address) }
    }

    override fun onHouseSelected(
        context: Context,
        housingStatus: HousingStatus
    ) {
        updateState {
            it.copy(
                houseBackend = housingStatus,
                houseType = housingStatus.toLabelResId(context)
            )
        }
    }

    override fun onNextButtonClicked() {
        updateState { it.copy(currentForm = uiState.value.currentForm + 1) }
    }

    override fun onBackButtonClicked() {
        updateState { it.copy(currentForm = uiState.value.currentForm - 1) }
    }

    override fun onIncomeValueChanged(income: String) {
        updateState { it.copy(monthlyIncome = income) }
    }

    override fun onNumberOfFamilySelected(number: String) {
        val newMembers = List(number.toInt()) { index ->
            uiState.value.familyMembers.getOrNull(index) ?: FamilyMember()
        }
        updateState {
            it.copy(familyCount = number.toInt()
            , familyMembers = newMembers)
        }
    }

    override fun onFamilyMemberChanged(
        index: Int,
        familyMember: FamilyMember
    ) {
        val members = uiState.value.familyMembers.toMutableList()
        members[index] = familyMember
        updateState { it.copy(familyMembers = members) }
    }

    override fun onCompleteRegistrationClicked() {
        Log.e("hzm", "onCompleteRegistrationClicked: ")
        updateState { it.copy(isLoading = true) }
        launchWithResult(
            action = { updateBeneficiaryDetailsUseCase.invoke(Beneficiary(
                nationalId = "",
                phone = "",
                address = uiState.value.address,
                healthStatus = uiState.value.healthStatusBackend,
                dateOfBirth = uiState.value.dateOfBarth,
                housingStatus = uiState.value.houseBackend,
                gender = uiState.value.genderBackend,
                income = uiState.value.monthlyIncome.toDouble(),
                fullName = ""
            ))},
            onSuccess = {
                Log.e("hzm", "update success")
                uiState.value.familyMembers.forEach {
                    launchWithResult(
                        action = { createNewFamilyMemberUseCase.invoke(com.app.domain.model.FamilyMember(
                            fullName = it.name,
                            nationalId = it.idNumber,
                            healthStatus = it.healthStatusBackend,
                            relationship = it.relationBackend,
                            dateOfBirth = it.dateOfBarth,
                            gender = it.genderBackend
                        ))},
                        onSuccess = {
                            Log.e("hzm", "add member success")
                        },
                        onError = {
                            Log.e("hzm", "add member failed")
                        },
                    )
                }
                updateState { it.copy(isLoading = false) }
                sendEvent(BeneficiaryDetailsEvents.NavigateToLoginScreen)
            },
            onError = {
                Log.e("hzm", "update failed")
            }
        )
    }

}