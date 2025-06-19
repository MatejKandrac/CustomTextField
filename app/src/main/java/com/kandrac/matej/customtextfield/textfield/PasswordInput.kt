package com.kandrac.matej.customtextfield.textfield

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kandrac.matej.customtextfield.ColorSurfaceDanger
import com.kandrac.matej.customtextfield.R
import com.kandrac.matej.customtextfield.TextStyleBodyMedium


typealias Validator = () -> String?

/**
 * All following parameters are the same as in InputView, with a few exceptions.
 * All changes are described below.
 * I chose to create two styles of password input to ensure good UX.
 * I found inspiration from following dribble post:
 * https://dribbble.com/shots/24131898-Password-Input-Field-States
 * This style shows all violations in real time. The second style only shows the first violation.
 *
 * I also decided to add a list of validators which can be added to validate the field. I also
 * added the onValidChanged property, so the views which would use this filed get a callback on the validation state.
 */
@Composable
fun PasswordInput(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    onValidChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier,

    labelText: String? = null,
    label: @Composable (() -> Unit)? = null,

    supportText: String? = null,
    support: @Composable (() -> Unit)? = null,

    placeholderText: String? = null,
    placeholder: @Composable (() -> Unit)? = null,

    textStyle: TextStyle? = null,

    shape: Shape? = null,

    labelFieldSpacing: Dp? = null,
    supportTextSpacing: Dp? = null,

    isError: Boolean = false,

    enabled: Boolean = true,
    readOnly: Boolean = false,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    colors: TextFieldColors? = null
) {
    Column(
        modifier = Modifier.padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        var text by remember { mutableStateOf(TextFieldValue("")) }

        val hasCorrectLength by remember {
            derivedStateOf {
                text.text.length > 8
            }
        }
        val hasUppercase by remember {
            derivedStateOf {
                text.text.any { it.isUpperCase() }
            }
        }

        val hasNumber by remember {
            derivedStateOf {
                text.text.any { it.isDigit() }
            }
        }

        val hasSpecialCharacter by remember {
            derivedStateOf {
                text.text.any { !it.isLetterOrDigit() }
            }
        }

        val isPasswordValid by remember {
            derivedStateOf {
                hasCorrectLength && hasUppercase && hasNumber && hasSpecialCharacter
            }
        }

        var showPassword by remember { mutableStateOf(false) }

        InputView(
            value = value,
            onValueChange = {
                text = it
            },
            modifier = Modifier.fillMaxWidth(),
            isError = !isPasswordValid,
            labelText = stringResource(R.string.enter_password),
            optionalText = stringResource(R.string.password_support_text),
            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(
                    onClick = {
                        showPassword = !showPassword
                    }
                ) {
                    Icon(
                        if (showPassword) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        "Visibility icon"
                    )
                }
            }
        )
        Column {
            CheckMark(stringResource(R.string.password_8_chars), !hasCorrectLength)
            CheckMark(stringResource(R.string.password_one_uppercase), !hasUppercase)
            CheckMark(stringResource(R.string.password_one_number), !hasNumber)
            CheckMark(stringResource(R.string.password_one_special), !hasSpecialCharacter)
        }
    }
}

@Composable
fun CheckMark(text: String, isError: Boolean) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            if (isError) Icons.Filled.Close else Icons.Filled.Check,
            contentDescription = "Checkmark",
            tint = if (isError) ColorSurfaceDanger else Color.Green
        )
        Text(text, style = TextStyleBodyMedium.merge(
            color = if (isError) ColorSurfaceDanger else Color.Black
        ))
    }
}

