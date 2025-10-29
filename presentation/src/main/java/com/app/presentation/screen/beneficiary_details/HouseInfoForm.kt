package com.app.presentation.screen.beneficiary_details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.design_system.component.text_field.WusalTextField
import com.app.design_system.theme.Theme
import com.app.domain.model.HousingStatus
import com.app.presentation.R
import com.app.presentation.utlis.toLabelResId

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HouseInfoForm(
    state: BeneficiaryDetailsState,
    interactionListener: BeneficiaryDetailsInteractionListener
){
    val context = LocalContext.current
    Column {
        Text(
            text = stringResource(R.string.housing_information),
            style = Theme.textStyle.bold.copy(fontSize = 20.sp),
            color = Theme.colors.shade.primary,
            modifier = Modifier
                .padding(top = 24.dp),
        )
        Text(
            text = stringResource(R.string.please_provide_your_current_address_and_housing_details),
            style = Theme.textStyle.regular.copy(fontSize = 14.sp),
            color = Theme.colors.shade.secondary,
            modifier = Modifier
                .padding(top = 8.dp),
        )
        WusalTextField(
            modifier = Modifier
                .padding(top = 16.dp),
            value = state.address,
            label = stringResource(R.string.current_address),
            leadingIcon = painterResource(R.drawable.address),
            leadingIconTint = Theme.colors.additional.iconColor,
            onValueChange = interactionListener::onAddressValueChanged,
            placeholder = stringResource(R.string.enter_your_current_address),
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
                value = state.houseType,
                label = stringResource(R.string.type_of_house),
                onValueChange = {},
                placeholder = stringResource(R.string.select),
                maxLines = 1,
                leadingIcon = painterResource(R.drawable.adress_type),
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
                HousingStatus.entries.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(text = option.toLabelResId(context = context)) },
                        onClick = {
                            interactionListener.onHouseSelected(context,option)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}