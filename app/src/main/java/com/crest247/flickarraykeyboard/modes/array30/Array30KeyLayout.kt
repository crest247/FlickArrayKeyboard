package com.crest247.flickarraykeyboard.modes.array30

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.crest247.flickarraykeyboard.core.models.KeyboardAction
import com.crest247.flickarraykeyboard.core.LocalKeyboardState
import com.crest247.flickarraykeyboard.core.engine.SystemAction
import com.crest247.flickarraykeyboard.core.models.FlickKeyData
import com.crest247.flickarraykeyboard.core.models.VisibleKeyData
import com.crest247.flickarraykeyboard.core.theme.LocalKeyboardDimens
import com.crest247.flickarraykeyboard.core.ui.components.StandardKeyboard
import com.crest247.flickarraykeyboard.core.ui.preview.KeyboardPreviewWrapper
import com.crest247.flickarraykeyboard.core.ui.preview.ThemePreviews
import com.crest247.flickarraykeyboard.modes.shared.array.ArrayProcessor

@Composable
fun Array30KeyLayout(processor: ArrayProcessor) {
    val dimens = LocalKeyboardDimens.current
    val state = LocalKeyboardState.current
    val systemProcessor = state.systemProcessor
    val imeOptions = state.currentEditorInfo?.imeOptions

    val keyRows = remember(imeOptions) {
        Array30LayoutProvider.createKeys(imeOptions)
    }
    StandardKeyboard(
        keyRows = keyRows,
        rowHeight = dimens.array30KeyHeight
    ) { keyData, direction ->
        when (keyData) {
            is FlickKeyData<*> -> keyData.directionActions[direction]
            is VisibleKeyData<*> -> keyData.action
            else -> null
        }?.let { it as? KeyboardAction }?.let { action ->
            if (!processor.onAction(action))
                (action as? SystemAction)?.let { systemProcessor.onAction(it) }
        }
    }
}

@ThemePreviews
@Composable
fun PreviewKeyLayout() {
    KeyboardPreviewWrapper {
        Array30KeyLayout(processor = ArrayProcessor())
    }
}