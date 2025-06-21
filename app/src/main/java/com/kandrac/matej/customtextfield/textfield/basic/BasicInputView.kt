package com.kandrac.matej.customtextfield.textfield.basic

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
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

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BasicInputView(
    value: TextFieldValue,
    onValueChanged: (TextFieldValue) -> Unit,
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

    supportingText: String? = null,
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
    isError: Boolean = false,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    innerPadding: PaddingValues = PaddingValues(
        start = SpacingMedium,
        top = SpacingSmall,
        end = SpacingExtraSmall,
        bottom = SpacingSmall,
    )
) {
    var isFocused by remember { mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.spacedBy(labelFieldSpacing),
        modifier = modifier
    ) {
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(optionalTextSpacing),
            verticalArrangement = Arrangement.Center
        ) {
            if (labelText != null)
                Text(
                    labelText,
                    style = labelTextStyle,
                    color = labelDefaultTextColor,
                )
            if (optionalText != null)
                Text(
                    optionalText,
                    modifier = Modifier.align(Alignment.CenterVertically),
                    style = optionalTextStyle,
                    color = optionalDefaultTextColor
                )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(supportTextSpacing)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = when {
                            isFocused -> focusedBorderWidth
                            else -> enabledBorderWidth
                        },
                        color = when {
                            !enabled -> disabledColor
                            isError -> errorColor
                            isFocused -> focusColor
                            else -> enabledColor
                        },
                        shape = shape
                    )
                    .onFocusChanged {
                        isFocused = it.isFocused
                    }
                    .defaultMinSize(minHeight = minHeight)
                    .padding(innerPadding)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(contentGap)
                ) {
                    if (prefix != null) prefix()
                    BasicTextField(
                        value = value,
                        onValueChange = onValueChanged,
                        visualTransformation = visualTransformation,
                        modifier = Modifier.weight(1f),
                        textStyle = textStyle,
                        enabled = enabled,
                        keyboardOptions = keyboardOptions,
                        readOnly = readOnly,
                        interactionSource = interactionSource,
                        keyboardActions = keyboardActions,
                        singleLine = singleLine,
                        maxLines = maxLines,
                        minLines = minLines,
                        decorationBox = { innerTextField ->
                            if (placeholderText != null && value.text.isEmpty())
                                Text(
                                    placeholderText,
                                    style = placeholderTextStyle,
                                    color = placeholderTextColor,
                                )
                            innerTextField()
                        }
                    )
                    if (suffix != null) Box(
                        modifier = Modifier.sizeIn(maxWidth = maxIconSize, maxHeight = maxIconSize)
                    ) {
                        suffix()
                    }
                }
            }
            if (supportingText != null) Box(
                modifier = Modifier.padding(start = supportTextLeftMargin)
            ) {
                Text(
                    supportingText,
                    style = supportingTextStyle,
                    color = when {
                        isError -> errorColor
                        else -> supportingTextColor
                    }
                )
            }
        }
    }
}
