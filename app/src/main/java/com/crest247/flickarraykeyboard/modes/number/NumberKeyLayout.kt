package com.crest247.flickarraykeyboard.modes.number

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.crest247.flickarraykeyboard.core.LocalKeyboardState
import com.crest247.flickarraykeyboard.core.models.CharKeyData
import com.crest247.flickarraykeyboard.core.models.FlickKeyData
import com.crest247.flickarraykeyboard.core.models.FuncKeyData
import com.crest247.flickarraykeyboard.core.theme.LocalKeyboardDimens
import com.crest247.flickarraykeyboard.core.ui.components.KeyContent
import com.crest247.flickarraykeyboard.core.ui.components.StandardKeyboard
import com.crest247.flickarraykeyboard.core.ui.preview.KeyboardPreviewWrapper
import com.crest247.flickarraykeyboard.core.ui.preview.ThemePreviews

@Composable
fun NumberKeyLayout(processor: NumberProcessor) {
    val dimens = LocalKeyboardDimens.current
    val state = LocalKeyboardState.current
    val systemProcessor = state.systemProcessor
    val imeOptions = state.currentEditorInfo?.imeOptions

    val keyRows = remember(imeOptions) {
        NumberLayoutProvider.createKeys(imeOptions)
    }
    StandardKeyboard(
        keyRows = keyRows,
        rowHeight = dimens.numberKeyHeight
    ) { keyData, direction ->
        val action = when (keyData) {
            is CharKeyData -> NumberAction.InputChar(keyData.text)
            is FuncKeyData -> when (keyData.type) {
                else -> null
            }

            is FlickKeyData -> {
                val targetContent: KeyContent? = keyData.popupContents.getOrNull(direction!!)
                when (targetContent) {
                    is KeyContent.Text -> {
                        NumberAction.InputChar(targetContent.text)
                    }
                    else -> null
                }
            }

            else -> null
        }

        if (action != null) {
            processor.onAction(action)
        } else {
            systemProcessor.handleUniversalKey(keyData)
        }
    }
}

@ThemePreviews
@Composable
fun PreviewKeyLayout() {
    KeyboardPreviewWrapper {
        NumberKeyLayout(processor = NumberProcessor())
    }
}