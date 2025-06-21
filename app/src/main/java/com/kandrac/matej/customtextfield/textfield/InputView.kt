package com.kandrac.matej.customtextfield.textfield

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
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

/**
 * This widget uses OutlinedTextField to create an input view.
 * It contains all possible customizations like a classic OutlinedTextView.
 *
 * Experimental api is enabled because i use FlowRow to wrap label and optionalText
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun InputView(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
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
    // I only changed the default color of error
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(
        errorBorderColor = ColorSurfaceDanger,
    )
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(labelFieldSpacing)
    ) {
        // FlowRow ensures that when the label text is too long, the optional text is on the next line
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(supportTextSpacing),
            verticalArrangement = Arrangement.Center
        ) {
            if (labelText != null) Text(
                labelText,
                style = labelTextStyle,
                color = if (isError) errorColor else labelDefaultTextColor,
            )
            if (optionalText != null) Text(
                optionalText,
                // This modifier is required in order to arrange value to center, so that baseline
                // of label and optional text is the same
                // Source: https://slack-chats.kotlinlang.org/t/12085939/has-anyone-been-able-to-successfully-use-flowrow-and-alignme
                modifier = Modifier.align(Alignment.CenterVertically),
                style = optionalTextStyle,
                color = optionalDefaultTextColor
            )
        }

        OutlinedTextField(
            value,
            textStyle = textStyle,
            modifier = modifier,
            onValueChange = onValueChange,
            shape = shape,
            isError = isError,
            enabled = enabled,
            readOnly = readOnly,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            prefix = prefix,
            suffix = suffix,
            supportingText = supportingText,
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            singleLine = singleLine,
            maxLines = maxLines,
            minLines = minLines,
            interactionSource = interactionSource,
            colors = colors,
            placeholder = {
                when {
                    placeholderText != null -> Text(
                        placeholderText,
                        style = placeholderTextStyle,
                        color = placeholderTextColor
                    )
                }
            }
        )
    }
}
