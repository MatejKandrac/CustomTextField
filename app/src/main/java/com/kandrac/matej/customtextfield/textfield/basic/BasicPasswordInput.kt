package com.kandrac.matej.customtextfield.textfield.basic

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kandrac.matej.customtextfield.ColorContentHigh
import com.kandrac.matej.customtextfield.ColorContentLow
import com.kandrac.matej.customtextfield.ColorContentMedium
import com.kandrac.matej.customtextfield.ColorSurfaceBrand
import com.kandrac.matej.customtextfield.ColorSurfaceDanger
import com.kandrac.matej.customtextfield.ColorSurfaceHigh
import com.kandrac.matej.customtextfield.ColorSurfaceLow
import com.kandrac.matej.customtextfield.SpacingExtraSmall
import com.kandrac.matej.customtextfield.SpacingMedium
import com.kandrac.matej.customtextfield.SpacingSmall
import com.kandrac.matej.customtextfield.TextInputRadius
import com.kandrac.matej.customtextfield.TextStyleBodyMedium
import com.kandrac.matej.customtextfield.TextStyleLabelMedium
import com.kandrac.matej.customtextfield.TextStyleLabelSmall

typealias Validator = (text: String) -> Int?

@Composable
fun BasicPasswordInput(
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

    placeholderText: String? = null,
    placeholderTextStyle: TextStyle = TextStyleBodyMedium,
    placeholderTextColor: Color = ColorContentLow,

    supportingTextStyle: TextStyle = TextStyleLabelSmall,
    supportingTextColor: Color = ColorContentMedium,

    // This parameter overrides default text style (inside input)
    textStyle: TextStyle = TextStyleBodyMedium,

    // You can change the spacing between label and field and also between label and optional text
    labelFieldSpacing: Dp = SpacingExtraSmall,
    optionalTextSpacing: Dp = SpacingExtraSmall,
    supportTextSpacing: Dp = SpacingExtraSmall,
    supportTextLeftMargin: Dp = SpacingSmall,

    // All colors for manipulation of border colors
    errorColor: Color = ColorSurfaceDanger,
    enabledColor: Color = ColorSurfaceHigh,
    disabledColor: Color = ColorSurfaceLow,
    focusColor: Color = ColorSurfaceBrand,

    enabledBorderWidth: Dp = 1.dp,
    focusedBorderWidth: Dp = 2.dp,

    contentGap: Dp = 3.dp,

    minHeight: Dp = 48.dp,
    maxIconSize: Dp = 30.dp,

    // All following parameters were added to ensure that every customization available for
    // OutlinedTextField is available. Default values were copied from default implementation of OutlinedTextField
    // or some specific ones (shape and colors) are from the Figma.
    shape: Shape = RoundedCornerShape(TextInputRadius),
    enabled: Boolean = true,
    readOnly: Boolean = false,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    visualTransformation: VisualTransformation = PasswordVisualTransformation(),

    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Password
    ),
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    innerPadding: PaddingValues = PaddingValues(
        start = SpacingMedium,
        top = SpacingSmall,
        end = SpacingExtraSmall,
        bottom = SpacingSmall,
    ),
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    var errorTextResource by remember { mutableStateOf<Int?>(null) }

    val isPasswordValid by remember {
        derivedStateOf {
            errorTextResource == null
        }
    }

    BasicInputView(
        value = value,
        onValueChanged = {
            val validatorResult = validator(it.text)
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
        optionalTextSpacing = optionalTextSpacing,
        placeholderText = placeholderText,
        placeholderTextStyle = placeholderTextStyle,
        placeholderTextColor = placeholderTextColor,
        supportingText = when {
            errorTextResource != null -> stringResource(errorTextResource!!)
            else -> null
        },
        supportingTextStyle = supportingTextStyle,
        supportTextSpacing = supportTextSpacing,
        supportTextLeftMargin = supportTextLeftMargin,
        supportingTextColor = supportingTextColor,
        textStyle = textStyle,
        labelFieldSpacing = labelFieldSpacing,
        errorColor = errorColor,
        shape = shape,
        enabled = enabled,
        readOnly = readOnly,
        prefix = leadingIcon,
        suffix = trailingIcon,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        maxLines = maxLines,
        minLines = minLines,
        interactionSource = interactionSource,
        visualTransformation = visualTransformation,
        enabledColor = enabledColor,
        disabledColor = disabledColor,
        focusColor = focusColor,
        enabledBorderWidth = enabledBorderWidth,
        focusedBorderWidth = focusedBorderWidth,
        contentGap = contentGap,
        minHeight = minHeight,
        maxIconSize = maxIconSize,
        innerPadding = innerPadding
    )
}