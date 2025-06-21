package com.kandrac.matej.customtextfield.textfield

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation

typealias Validator = (text: String) -> Int?

/**
 * All following parameters are the same as in InputView, with a few exceptions.
 * All changes are described below.
 */
@Composable
fun PasswordInput(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    onValidChanged: (Boolean) -> Unit,
    validator: Validator,

    labelText: String? = null,
    optionalText: String? = null,
    placeholderText: String? = null,

    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Password
    ),
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = PasswordVisualTransformation(),
) {
    var errorTextResource by remember { mutableStateOf<Int?>(null) }

    val isPasswordValid by remember {
        derivedStateOf {
            errorTextResource == null
        }
    }

    InputView(
        value = value,
        onValueChange = {
            val validatorResult = validator(it.text)
            println("Current valid state $isPasswordValid, new: (${validatorResult == null})")
            if (isPasswordValid != (validatorResult == null))
                onValidChanged(!isPasswordValid)
            errorTextResource = validatorResult
            onValueChange(it)
        },
        modifier = Modifier.fillMaxWidth(),
        isError = !isPasswordValid,
        labelText = labelText,
        optionalText = optionalText,
        placeholderText = placeholderText,
        visualTransformation = visualTransformation,
        trailingIcon = trailingIcon,
        keyboardOptions = keyboardOptions,
        supportingText = {
            if (errorTextResource != null) Text(stringResource(errorTextResource!!))
        }
    )
}
