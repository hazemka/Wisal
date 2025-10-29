package com.app.presentation.screen.beneficiary_details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.design_system.component.text_field.WusalTextField
import com.app.design_system.theme.Theme
import com.app.domain.model.Gender
import com.app.domain.model.HealthStatus
import com.app.presentation.R
import com.app.presentation.utlis.toLabelResId
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonalInfoForm(
    state: BeneficiaryDetailsState,
    interactionListener: BeneficiaryDetailsInteractionListener
) {
    val context = LocalContext.current
    var showDatePicker by remember { mutableStateOf(false) }
    Column {
        Text(
            text = stringResource(R.string.personal_information),
            style = Theme.textStyle.bold.copy(fontSize = 20.sp),
            color = Theme.colors.shade.primary,
            modifier = Modifier
                .padding(top = 24.dp),
        )
        Text(
            text = stringResource(R.string.please_provide_your_basic_personal_data),
            style = Theme.textStyle.regular.copy(fontSize = 14.sp),
            color = Theme.colors.shade.secondary,
            modifier = Modifier
                .padding(top = 8.dp),
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = { showDatePicker = true })
        ) {
            WusalTextField(
                modifier = Modifier
                    .padding(top = 16.dp),
                value = state.dateOfBarth,
                label = stringResource(R.string.date_of_barth),
                onValueChange = {},
                placeholder = stringResource(R.string.click_to_enter_your_date_of_barth),
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
                            interactionListener.onDateSelected(formattedDate)
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
                value = state.gender,
                label = stringResource(R.string.gender),
                onValueChange = {},
                placeholder = stringResource(R.string.select),
                maxLines = 1,
                leadingIcon = painterResource(R.drawable.profile_ic),
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
                Gender.entries.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(text = option.toLabelResId(context = context)) },
                        onClick = {
                            interactionListener.onGenderSelected(context,option)
                            expanded = false
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
                value = state.healthStatus,
                label = stringResource(R.string.health_status),
                onValueChange = {},
                placeholder = stringResource(R.string.select),
                maxLines = 1,
                leadingIcon = painterResource(R.drawable.health),
                leadingIconTint = Theme.colors.shade.secondary,
                enabled = false,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
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
                            interactionListener.onHealthStatusSelected(context,option)
                            expandedHealthStatus = false
                        }
                    )
                }
            }
        }
    }
}

