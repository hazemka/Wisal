package com.app.presentation.screen.beneficiary_details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.design_system.component.button.WusalButton
import com.app.design_system.component.text_field.WusalTextField
import com.app.design_system.theme.Theme
import com.app.domain.model.Gender
import com.app.domain.model.HealthStatus
import com.app.domain.model.Relation
import com.app.presentation.R
import com.app.presentation.utlis.toLabelResId
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FamilyInfoForm(
    state: BeneficiaryDetailsState,
    interactionListener: BeneficiaryDetailsInteractionListener
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(bottom = 32.dp)
    ) {
        Text(
            text = stringResource(R.string.family_information),
            style = Theme.textStyle.bold.copy(fontSize = 20.sp),
            color = Theme.colors.shade.primary,
            modifier = Modifier
                .padding(top = 24.dp),
        )
        Text(
            text = stringResource(R.string.tell_us_about_your_marital_status_and_family_details),
            style = Theme.textStyle.regular.copy(fontSize = 14.sp),
            color = Theme.colors.shade.secondary,
            modifier = Modifier
                .padding(top = 8.dp),
        )
        WusalTextField(
            modifier = Modifier
                .padding(top = 16.dp),
            value = state.monthlyIncome,
            label = stringResource(R.string.family_monthly_income_in_shekels),
            leadingIcon = painterResource(R.drawable.money),
            leadingIconTint = Theme.colors.additional.iconColor,
            onValueChange = interactionListener::onIncomeValueChanged,
            placeholder = stringResource(R.string.enter_your_monthly_income),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Number,
            ),
            maxLines = 1,
        )

        var expanded by remember { mutableStateOf(false) }

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            // The "TextField" that shows the current selection
            val shape = RoundedCornerShape(8.dp)

            WusalTextField(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .menuAnchor(),
                value = state.familyCount.toString(),
                label = stringResource(R.string.number_of_family_members),
                onValueChange = {},
                maxLines = 1,
                leadingIcon = painterResource(R.drawable.family_ic),
                leadingIconTint = Theme.colors.shade.secondary,
                enabled = false,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            )

            // The dropdown list
            ExposedDropdownMenu(
                containerColor = Theme.colors.background.card,
                shape = shape,
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                (1..10).toList().forEach { option ->
                    DropdownMenuItem(
                        text = { Text(text = option.toString()) },
                        onClick = {
                            interactionListener.onNumberOfFamilySelected(option.toString())
                            expanded = false
                        }
                    )
                }
            }
        }
        state.familyMembers.forEachIndexed { index, member ->
            var showDatePicker by remember { mutableStateOf(false) }
            Text(
                text = stringResource(R.string.family_member_num, index + 1),
                style = Theme.textStyle.bold.copy(fontSize = 16.sp),
                modifier = Modifier.padding(vertical = 16.dp)
            )
            WusalTextField(
                modifier = Modifier
                    .padding(top = 8.dp),
                value = member.name,
                label = stringResource(R.string.full_name),
                leadingIcon = painterResource(R.drawable.profile_ic),
                leadingIconTint = Theme.colors.additional.iconColor,
                onValueChange = {
                    interactionListener.onFamilyMemberChanged(
                        index,
                        member.copy(name = it)
                    )
                },
                placeholder = stringResource(R.string.enter_the_full_name),
                maxLines = 1,
            )
            WusalTextField(
                modifier = Modifier
                    .padding(top = 16.dp),
                value = member.idNumber,
                label = stringResource(R.string.id_number),
                leadingIcon = painterResource(R.drawable.id_ic),
                leadingIconTint = Theme.colors.additional.iconColor,
                onValueChange = {
                    interactionListener.onFamilyMemberChanged(
                        index,
                        member.copy(idNumber = it)
                    )
                },
                placeholder = stringResource(R.string.enter_the_id_number),
                maxLines = 1,
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = { showDatePicker = true })
            ) {
                WusalTextField(
                    modifier = Modifier
                        .padding(top = 16.dp),
                    value = member.dateOfBarth,
                    label = stringResource(R.string.date_of_barth),
                    onValueChange = {},
                    placeholder = stringResource(R.string.enter_the_date_of_barth),
                    maxLines = 1,
                    leadingIcon = painterResource(R.drawable.date),
                    leadingIconTint = Theme.colors.shade.secondary,
                    enabled = false
                )
            }
            if (showDatePicker) {
                val datePickerState = rememberDatePickerState()
                DatePickerDialog(
                    onDismissRequest = { showDatePicker = false },
                    confirmButton = {
                        TextButton(onClick = {
                            datePickerState.selectedDateMillis?.let {
                                val formattedDate =
                                    java.text.SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                                        .format(java.util.Date(it))
                                interactionListener.onFamilyMemberChanged(
                                    index,
                                    member.copy(dateOfBarth = formattedDate)
                                )
                            }
                            showDatePicker = false
                        }) {
                            Text(stringResource(R.string.ok))
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { showDatePicker = false }) {
                            Text(stringResource(R.string.cancel))
                        }
                    }
                ) {
                    DatePicker(state = datePickerState)
                }
            }


            var expandedGender by remember { mutableStateOf(false) }

            ExposedDropdownMenuBox(
                expanded = expandedGender,
                onExpandedChange = { expandedGender = !expandedGender }
            ) {
                // The "TextField" that shows the current selection
                val shape = RoundedCornerShape(8.dp)

                WusalTextField(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .menuAnchor(),
                    value = member.gender,
                    label = stringResource(R.string.gender),
                    onValueChange = {},
                    placeholder = stringResource(R.string.select),
                    maxLines = 1,
                    leadingIcon = painterResource(R.drawable.profile_ic),
                    leadingIconTint = Theme.colors.shade.secondary,
                    enabled = false,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedGender) },
                )

                // The dropdown list
                ExposedDropdownMenu(
                    containerColor = Theme.colors.background.card,
                    shape = shape,
                    expanded = expandedGender,
                    onDismissRequest = { expandedGender = false }
                ) {
                    Gender.entries.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(text = option.toLabelResId(context = context)) },
                            onClick = {
                                interactionListener.onFamilyMemberChanged(
                                    index,
                                    member.copy(
                                        genderBackend = option,
                                        gender = option.toLabelResId(context)
                                    )
                                )
                                expandedGender = false
                            }
                        )
                    }
                }
            }

            var expandedHealthStatus by remember { mutableStateOf(false) }

            ExposedDropdownMenuBox(
                expanded = expandedHealthStatus,
                onExpandedChange = { expandedHealthStatus = !expandedHealthStatus }
            ) {
                // The "TextField" that shows the current selection
                val shape = RoundedCornerShape(8.dp)

                WusalTextField(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .menuAnchor(),
                    value = member.healthStatus,
                    label = stringResource(R.string.health_status),
                    onValueChange = {},
                    placeholder = stringResource(R.string.select),
                    maxLines = 1,
                    leadingIcon = painterResource(R.drawable.health),
                    leadingIconTint = Theme.colors.shade.secondary,
                    enabled = false,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedHealthStatus) },
                )

                // The dropdown list
                ExposedDropdownMenu(
                    containerColor = Theme.colors.background.card,
                    shape = shape,
                    expanded = expandedHealthStatus,
                    onDismissRequest = { expandedHealthStatus = false }
                ) {
                    HealthStatus.entries.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(text = option.toLabelResId(context = context)) },
                            onClick = {
                                interactionListener.onFamilyMemberChanged(
                                    index,
                                    member.copy(
                                        healthStatusBackend = option,
                                        healthStatus = option.toLabelResId(context)
                                    )
                                )
                                expandedHealthStatus = false
                            }
                        )
                    }
                }
            }

            var expandedRelation by remember { mutableStateOf(false) }

            ExposedDropdownMenuBox(
                expanded = expandedRelation,
                onExpandedChange = { expandedRelation = !expandedRelation }
            ) {
                // The "TextField" that shows the current selection
                val shape = RoundedCornerShape(8.dp)

                WusalTextField(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .menuAnchor(),
                    value = member.relation,
                    label = stringResource(R.string.relation),
                    onValueChange = {},
                    placeholder = stringResource(R.string.select),
                    maxLines = 1,
                    leadingIcon = painterResource(R.drawable.relation),
                    leadingIconTint = Theme.colors.shade.secondary,
                    enabled = false,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedRelation) },
                )

                // The dropdown list
                ExposedDropdownMenu(
                    containerColor = Theme.colors.background.card,
                    shape = shape,
                    expanded = expandedRelation,
                    onDismissRequest = { expandedRelation = false }
                ) {
                    Relation.entries.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(text = option.toLabelResId(context = context)) },
                            onClick = {
                                interactionListener.onFamilyMemberChanged(
                                    index,
                                    member.copy(
                                        relationBackend = option,
                                        relation = option.toLabelResId(context)
                                    )
                                )
                                expandedRelation = false
                            }
                        )
                    }
                }
            }
        }
        WusalButton(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
            buttonText = stringResource(R.string.complete_registration),
            buttonColor = Theme.colors.button.primary,
            textColor = Theme.colors.additional.white,
            textStyle = Theme.textStyle.medium.copy(fontSize = 16.sp),
            onClick = { interactionListener.onCompleteRegistrationClicked() },
        )
    }
}