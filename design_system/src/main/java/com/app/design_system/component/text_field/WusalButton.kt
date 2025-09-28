package com.app.design_system.component.text_field

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.design_system.R
import com.app.design_system.theme.Theme
import com.app.design_system.theme.WuslaTheme

@Composable
fun WusalButton(
    buttonText: String,
    textColor: Color,
    textStyle: TextStyle,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    textPadding: PaddingValues = PaddingValues(vertical = 12.dp),
    buttonColor: Color = Color.Transparent,
    cornerRadius: Dp = 12.dp,
    enableAction: Boolean = true,
    isLoading: Boolean = false,
    rightIcon: Painter ?= null,
    leftIcon: Painter ?= null,
) {
    val textColorAction by animateColorAsState(if (!enableAction) Theme.colors.shade.tertiary else textColor)

    val backgroundColor by animateColorAsState(
        if (buttonColor != Color.Transparent) {
            if (enableAction) buttonColor else Theme.colors.button.disabled
        } else {
            buttonColor
        }
    )

    val borderModifier = if (!enableAction) {
        Modifier.border(
            width = 1.dp,
            color = Theme.colors.shade.quaternary,
            shape = RoundedCornerShape(cornerRadius)
        )
    } else Modifier

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(
                shape = RoundedCornerShape(cornerRadius)
            )
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(cornerRadius)
            )
            .then(borderModifier)
            .clickable(
                enabled = enableAction,
                onClick = onClick
            ),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (leftIcon != null){
            Image(
                painter = leftIcon,
                contentDescription = "left icon",
                modifier = Modifier.size(20.dp)
                    .padding(end = 6.dp),
                colorFilter = ColorFilter.tint(textColor)
            )
        }
        Text(
            modifier = Modifier.padding(textPadding),
            text = buttonText,
            color = textColorAction,
            style = textStyle
        )
        if (rightIcon != null){
            Image(
                painter = rightIcon,
                contentDescription = "right icon",
                modifier = Modifier.size(20.dp)
                    .padding(start = 6.dp),
                colorFilter = ColorFilter.tint(textColor)
            )
        }
    }
}

@Preview
@Composable
private fun PreviewButton() {
    var isLoading by remember { mutableStateOf(true) }
    var isEnabled by remember { mutableStateOf(false) }

    WuslaTheme {

        WusalButton(
            buttonColor = Theme.colors.button.primary,
            buttonText = "Login",
            textColor = Theme.colors.additional.white,
            textStyle = Theme.textStyle.medium.copy(fontSize = 16.sp),
            isLoading = isLoading,
            enableAction = isEnabled,
            onClick = {
                isEnabled = !isEnabled
            },
            rightIcon = painterResource(R.drawable.eye_ic),
            leftIcon = painterResource(R.drawable.eye_ic)
        )
    }
}