package com.kandrac.matej.customtextfield

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * This file contains constants extracted from provided figma file.
 * I copied colors, font and text theme
 */

// COLORS
val ColorSurfaceHigh = Color(0xff8c8c9a)
val ColorSurfaceLow = Color(0xffffffff)
val ColorSurfaceBrand = Color(0xff0050ff)
val ColorSurfaceDanger = Color(0xffdc2828)
val ColorSurfaceDangerVariant = Color(0xffffdcdc)
val ColorSurfaceWarning = Color(0xffa56315)
val ColorSurfaceWarningVariant = Color(0xfffaf1b6)

val ColorContentHigh = Color(0xff2c2c31)
val ColorContentMedium = Color(0xff8c8c9a)
val ColorContentLow = Color(0xffc9c9ce)
val ColorContentDanger = Color(0xffdc2828)
// Wrong color was provided in figma, so i found the correct color
val ColorContentWarning = Color(0xffD32F2F)

val ColorStateHover = Color(0x0f1a1a1a)
val ColorStateFocus = Color(0xcc1a1a1a)

// FONT
// Font Inter was downloaded from official google fonts site:
// https://fonts.google.com/specimen/Inter?query=Inter
private val interFontFamily = FontFamily(
    Font(R.font.inter_regular),
    Font(R.font.inter_italic, style = FontStyle.Italic)
)

// TEXTS
val TextStyleLabelMedium = TextStyle(
    fontFamily = interFontFamily,
    fontSize = 16.sp,
    fontWeight = FontWeight(500),
    lineHeight = 22.sp,
    letterSpacing = TextUnit(0.16f, TextUnitType.Sp),
)

val TextStyleLabelSmall = TextStyle(
    fontFamily = interFontFamily,
    fontSize = 14.sp,
    fontWeight = FontWeight(550),
    lineHeight = 17.sp,
    letterSpacing = TextUnit(0.16f, TextUnitType.Sp)
)

val TextStyleBodyMedium = TextStyle(
    fontFamily = interFontFamily,
    fontSize = 16.sp,
    fontWeight = FontWeight(400),
    lineHeight = 22.sp,
    letterSpacing = TextUnit(0.01f, TextUnitType.Sp)
)


val TextInputRadius = 12.dp

val SpacingExtraSmall = 8.dp
val SpacingSmall = 12.dp
val SpacingMedium = 16.dp
val SpacingLarge = 20.dp
