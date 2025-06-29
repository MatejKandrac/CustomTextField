package com.kandrac.matej.customtextfield.textfield

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import com.kandrac.matej.customtextfield.ColorContentHigh
import com.kandrac.matej.customtextfield.ColorContentLow
import com.kandrac.matej.customtextfield.ColorContentMedium
import com.kandrac.matej.customtextfield.ColorSurfaceDanger
import com.kandrac.matej.customtextfield.SpacingExtraSmall
import com.kandrac.matej.customtextfield.TextInputRadius
import com.kandrac.matej.customtextfield.TextStyleBodyMedium
import com.kandrac.matej.customtextfield.TextStyleLabelMedium
import com.kandrac.matej.customtextfield.TextStyleLabelSmall

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

    modifier: Modifier = Modifier,

    labelText: String? = null,
    labelTextStyle: TextStyle = TextStyleLabelMedium,
    labelDefaultTextColor: Color = ColorContentHigh,

    optionalText: String? = null,
    optionalTextStyle: TextStyle = TextStyleLabelSmall,
    optionalDefaultTextColor: Color = ColorContentMedium,

    // Placeholder texts work the same as the above
    placeholderText: String? = null,
    placeholderTextStyle: TextStyle = TextStyleBodyMedium,
    placeholderTextColor: Color = ColorContentLow,

    // This parameter overrides default text style (inside input)
    textStyle: TextStyle = TextStyleBodyMedium,
    // You can change the spacing between label and field and also between label and optional text
    labelFieldSpacing: Dp = SpacingExtraSmall,
    supportTextSpacing: Dp = SpacingExtraSmall,

    // Error styles
    errorColor: Color = ColorSurfaceDanger,

    // All following parameters were added to ensure that every customization available for
    // OutlinedTextField is available. Default values were copied from default implementation of OutlinedTextField
    // or some specific ones (shape and colors) are from the Figma.
    shape: Shape = RoundedCornerShape(TextInputRadius),
    enabled: Boolean = true,
    readOnly: Boolean = false,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    // I changed default to password
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    // I only changed the default color of error
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(
        errorBorderColor = ColorSurfaceDanger,
    )
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
        modifier = modifier,
        isError = !isPasswordValid,
        labelText = labelText,
        labelTextStyle = labelTextStyle,
        labelDefaultTextColor = labelDefaultTextColor,
        optionalText = optionalText,
        optionalTextStyle = optionalTextStyle,
        optionalDefaultTextColor = optionalDefaultTextColor,
        placeholderText = placeholderText,
        placeholderTextStyle = placeholderTextStyle,
        placeholderTextColor = placeholderTextColor,
        textStyle = textStyle,
        labelFieldSpacing = labelFieldSpacing,
        supportTextSpacing = supportTextSpacing,
        errorColor = errorColor,
        shape = shape,
        enabled = enabled,
        readOnly = readOnly,
        leadingIcon = leadingIcon,
        prefix = prefix,
        suffix = suffix,
        supportingText = { if (errorTextResource != null) Text(stringResource(errorTextResource!!)) },
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        maxLines = maxLines,
        minLines = minLines,
        interactionSource = interactionSource,
        colors = colors,
        visualTransformation = visualTransformation,
        trailingIcon = trailingIcon,
    )
}
