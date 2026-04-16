package com.crest247.flickarraykeyboard.core.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit

data class KeyboardDimens(
    val keyHeight: Dp,
    val keyBigHeight: Dp,
    val keyTextSize: TextUnit,
    val keyIconSize: Dp,
    val candidateVerticalPadding: Dp,
    val candidateHorizontalPadding: Dp,
    val candidateHeight: Dp,
    val candidateTextSize: TextUnit,
    val radicalHeight: Dp,
    val radicalTextSize: TextUnit,
    val keyPadding: Dp,
    val keyRadius: Dp,
    val previewTextSize: TextUnit,
    val previewSmallTextSize: TextUnit,
    val previewIconSize: Dp,
    val previewSmallIconSize: Dp,
    val flickThreshold: Dp,
    val previewScale: Float,
    val previewGap: Dp,
    val previewTransparentPadding: Dp,
    val previewCornerRadius: Dp,
    val previewBorderWidth: Dp
)

val LocalKeyboardDimens = compositionLocalOf<KeyboardDimens> {
    error("KeyboardDimens not provided. Did you forget to wrap your UI in KeyboardTheme?")
}