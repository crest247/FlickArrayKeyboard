package com.crest247.flickarraykeyboard.core.ui.areas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import com.crest247.flickarraykeyboard.core.KeyboardState
import com.crest247.flickarraykeyboard.core.theme.LocalKeyboardDimens
import com.crest247.flickarraykeyboard.core.ui.components.KeyPreviewOverlay
import com.crest247.flickarraykeyboard.core.ui.components.LocalPreviewHandler
import com.crest247.flickarraykeyboard.core.ui.components.keyboardBackgroundStyle

@Composable
fun MainKeyboardContainer(
    state: KeyboardState,
    onKeyboardLayoutChanged: (LayoutCoordinates) -> Unit,
    onRadicalLayoutChanged: (LayoutCoordinates?) -> Unit
) {
    val previewHandler = LocalPreviewHandler.current
    val dimens = LocalKeyboardDimens.current
    val transparentTopPadding = dimens.previewTransparentPadding

    Box(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(transparentTopPadding)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                state.currentModule.SpellingAreaLayout(
                    onRadicalLayoutChanged = onRadicalLayoutChanged
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .keyboardBackgroundStyle()
                    .onGloballyPositioned { onKeyboardLayoutChanged(it) }
            ) {
                state.currentModule.SuggestionAreaLayout()
                state.currentModule.KeyAreaLayout()
            }
            Spacer(
                Modifier
                    .keyboardBackgroundStyle()
                    .windowInsetsBottomHeight(WindowInsets.navigationBars)
            )
        }
        previewHandler.activePreviews.forEach { previewState ->
            KeyPreviewOverlay(state = previewState)
        }
    }
}