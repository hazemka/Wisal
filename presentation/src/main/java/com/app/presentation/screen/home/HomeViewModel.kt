package com.app.presentation.screen.home

import android.content.Context
import com.app.presentation.R
import com.app.presentation.base.BaseViewModel

class HomeViewModel(
    private val context: Context
) : BaseViewModel<HomeState, HomeScreenEvents>(HomeState()) {

    init {
        updateState { it.copy(isLoading = true) }
        updateState {
            it.copy(
                posts = listOf(
                    PostUiModel(
                        title = context.getString(R.string.food_distribution_in_gaza),
                        content = context.getString(R.string.wfp_distributed_emergency_food_parcels_to_over_1_000_families_in_gaza_this_month),
                        summary = context.getString(R.string.ensuring_food_security_for_vulnerable_families),
                        imageUrl = R.drawable.wfp,
                        institutionName = context.getString(R.string.wfp)
                    ),
                    PostUiModel(
                        title = context.getString(R.string.school_supplies_for_children),
                        content = context.getString(R.string.unrwa_provided_essential_school_supplies_to_800_refugee_children_in_gaza),
                        summary = context.getString(R.string.supporting_education_for_refugee_children),
                        imageUrl = R.drawable.unrwa,
                        institutionName = context.getString(R.string.unrwa)
                    ),
                    PostUiModel(
                        title = context.getString(R.string.medical_aid_for_refugees),
                        content = context.getString(R.string.anira_s_medical_team_provided_free_health_checkups_and_medications_for_more_than_500_refugees),
                        summary = context.getString(R.string.healthcare_access_for_all),
                        imageUrl = R.drawable.anira,
                        institutionName = context.getString(R.string.anera)
                    ),
                )
            )
        }
        updateState { it.copy(isLoading = false) }
    }
}