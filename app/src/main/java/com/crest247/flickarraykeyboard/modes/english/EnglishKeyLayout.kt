package com.crest247.flickarraykeyboard.modes.english

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.crest247.flickarraykeyboard.core.LocalKeyboardState
import com.crest247.flickarraykeyboard.core.engine.SystemAction
import com.crest247.flickarraykeyboard.core.models.CharKeyData
import com.crest247.flickarraykeyboard.core.models.FuncKeyData
import com.crest247.flickarraykeyboard.core.models.FuncType
import com.crest247.flickarraykeyboard.core.ui.components.StandardKeyboard
import com.crest247.flickarraykeyboard.core.ui.preview.KeyboardPreviewWrapper
import com.crest247.flickarraykeyboard.core.ui.preview.ThemePreviews

@Composable
fun EnglishKeyLayout(processor: EnglishProcessor) {
    val state = LocalKeyboardState.current
    val systemProcessor = state.systemProcessor
    val imeOptions = state.currentEditorInfo?.imeOptions
    val shiftState = processor.shiftState
    val variant = processor.variant

    val keyRows = remember(shiftState, variant, imeOptions) {
        EnglishLayoutProvider.createKeys(shiftState, variant, imeOptions)
    }
    StandardKeyboard(keyRows = keyRows) { keyData, _ ->
        when (keyData) {
            is CharKeyData -> {
                processor.onAction(EnglishAction.InputChar(keyData.text))
            }

            is FuncKeyData -> {
                when (keyData.type) {
                    FuncType.BACKSPACE -> systemProcessor.onAction(SystemAction.Backspace)
                    FuncType.ENTER -> systemProcessor.onAction(SystemAction.Enter)
                    FuncType.LANGUAGE -> systemProcessor.onAction(SystemAction.SwitchLanguage)
                    FuncType.SHIFT -> processor.onAction(EnglishAction.ToggleShift)
                    FuncType.SPACE -> processor.onAction(EnglishAction.InputChar(" "))
                    else -> {}
                }
            }

            else -> {}
        }
    }
}

@ThemePreviews
@Composable
fun PreviewKeyLayout() {
    KeyboardPreviewWrapper {
        EnglishKeyLayout(processor = EnglishProcessor())
    }
}