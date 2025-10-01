package com.app.design_system.component.text_field

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.design_system.R
import com.app.design_system.theme.Theme
import com.app.design_system.theme.WuslaTheme

@Composable
fun WusalTextField(
    value: String,
    leadingIcon: Painter,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    placeholder: String? = null,
    isError: Boolean = false,
    errorMessage: String? = null,
    isPassword: Boolean = false,
    label: String? = null,
    leadingIconTint: Color = Color.Unspecified,
    maxLines: Int = 1,
    singleLine: Boolean = true,
    enabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    trailingIcon: @Composable (() -> Unit)? = null,
    forgotPasswordClick: (() -> Unit)? = null
) {
    var passwordVisible by remember { mutableStateOf(false) }
    var isFocused by remember { mutableStateOf(false) }

    val shape = RoundedCornerShape(8.dp)
    val borderColor = when {
        isError -> Theme.colors.stroke.error
        isFocused -> Theme.colors.stroke.focused
        else -> Theme.colors.stroke.normal
    }

    val shadeTertiaryColor = Theme.colors.shade.tertiary
    val additionalPrimaryRedColor = Theme.colors.stroke.error

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(space = 8.dp)
    ) {
        if (label != null) {
            BasicText(
                text = label,
                style = Theme.textStyle.medium,
                color = { shadeTertiaryColor }
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape)
                .background(Theme.colors.background.card, shape)
                .border(width = 1.dp, color = borderColor, shape = shape)
                    .padding(horizontal = 10.dp, vertical = 12.dp)
        ) {
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                singleLine = singleLine,
                maxLines = maxLines,
                enabled = enabled,
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                visualTransformation = if (isPassword && !passwordVisible) {
                    PasswordVisualTransformation()
                } else {
                    VisualTransformation.None
                },
                textStyle = Theme.textStyle.regular.copy(color = Theme.colors.shade.primary),
                cursorBrush = SolidColor(Theme.colors.brand),
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged { isFocused = it.isFocused },
                decorationBox = { innerTextField ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Image(
                            painter = leadingIcon,
                            contentDescription = null,
                            colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(
                                leadingIconTint
                            ),
                            modifier = Modifier.size(20.dp)
                        )

                        Spacer(modifier = Modifier.width(18.dp))

                        Box(modifier = Modifier.weight(1f)) {
                            if (value.isEmpty() && !placeholder.isNullOrEmpty()) {
                                BasicText(
                                    text = placeholder,
                                    style = Theme.textStyle.regular.copy(
                                        color = Theme.colors.shade.quaternary, fontSize = 16.sp
                                    ),
                                )
                            }
                            innerTextField()
                        }

                        when {
                            isPassword -> {
                                val image = if (passwordVisible) {
                                    painterResource(id = R.drawable.eye_ic)
                                } else {
                                    painterResource(id = R.drawable.eye_close_ic)
                                }
                                Image(
                                    painter = image,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(20.dp)
                                        .clickable(
                                            interactionSource = remember { MutableInteractionSource() },
                                            indication = null
                                        ) { passwordVisible = !passwordVisible },
                                    colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(
                                        Theme.colors.shade.secondary
                                    )
                                )
                            }

                            isError -> {
                                Image(
                                    painter = painterResource(id = R.drawable.triangle_danger_ic),
                                    contentDescription = null,
                                    colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(
                                        Theme.colors.stroke.error
                                    ),
                                    modifier = Modifier.size(16.dp)
                                )
                            }

                            trailingIcon != null -> trailingIcon()
                        }
                    }
                }
            )
        }

        if (isError && !errorMessage.isNullOrEmpty()) {
            BasicText(
                text = errorMessage,
                style = Theme.textStyle.regular,
                color = { additionalPrimaryRedColor }
            )
        }

        // look here .............
//        if (isPassword) {
//            Row(
//                horizontalArrangement = Arrangement.End,
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                BasicText(
//                    text = stringResource(id = R.string.forgot_password),
//                    style = Theme.textStyle.body.medium.regular.copy(
//                        textDecoration = TextDecoration.Underline
//                    ),
//                    color = { shadeTertiaryColor },
//                    modifier = Modifier.clickable(
//                        interactionSource = remember { MutableInteractionSource() },
//                        indication = null
//                    ) {
//                        forgotPasswordClick?.invoke()
//                    }
//                )
//            }
//        }
    }
}

@Preview
@Composable
private fun PreviewBasicAppTextField() {
    WuslaTheme {
        var text by remember { mutableStateOf("") }
        WusalTextField(
            label = "Full Name",
            value = text,
            onValueChange = { text = it },
            placeholder = "Enter your name",
            leadingIcon = painterResource(R.drawable.eye_ic),
            leadingIconTint = Theme.colors.additional.iconColor
        )
    }
}