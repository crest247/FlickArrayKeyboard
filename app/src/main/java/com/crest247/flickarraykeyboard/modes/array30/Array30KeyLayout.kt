package com.crest247.flickarraykeyboard.modes.array30

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.crest247.flickarraykeyboard.core.LocalKeyboardState
import com.crest247.flickarraykeyboard.core.engine.SystemAction
import com.crest247.flickarraykeyboard.core.models.FlickKeyData
import com.crest247.flickarraykeyboard.core.models.FuncKeyData
import com.crest247.flickarraykeyboard.core.models.FuncType
import com.crest247.flickarraykeyboard.core.models.TapKeyData
import com.crest247.flickarraykeyboard.core.theme.LocalKeyboardDimens
import com.crest247.flickarraykeyboard.core.ui.components.StandardKeyboard
import com.crest247.flickarraykeyboard.core.ui.preview.KeyboardPreviewWrapper
import com.crest247.flickarraykeyboard.core.ui.preview.ThemePreviews
import com.crest247.flickarraykeyboard.modes.shared.array.ArrayAction

@Composable
fun Array30KeyLayout(processor: Array30Processor) {
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
        val action = when (keyData) {
            is TapKeyData<*> -> keyData.action as? ArrayAction
            is FuncKeyData -> when (keyData.type) {
                FuncType.SPACE -> ArrayAction.Space
                FuncType.BACKSPACE -> ArrayAction.Backspace
                FuncType.ENTER -> ArrayAction.Enter
                else -> null
            }

            else -> null
        }

        if (action != null) {
            processor.onAction(action)
        } else {
            if (keyData is FlickKeyData<*>)
                keyData.directionActions[direction].let {
                    if (it is SystemAction) systemProcessor.onAction(it)
                }
            systemProcessor.handleUniversalKey(keyData)
        }
    }
}

@ThemePreviews
@Composable
fun PreviewKeyLayout() {
    KeyboardPreviewWrapper {
        Array30KeyLayout(processor = Array30Processor())
    }
}